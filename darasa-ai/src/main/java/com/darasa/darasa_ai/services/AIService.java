package com.darasa.darasa_ai.services;

import com.darasa.darasa_ai.dto.GeneratedQuestionDTO;
import com.darasa.darasa_ai.dto.QuestionGenerationRequest;
import com.darasa.darasa_ai.dto.QuestionGenerationResponse;
import com.darasa.darasa_ai.model.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AIService {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Autowired
    private CourseService courseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public QuestionGenerationResponse generateQuestions(QuestionGenerationRequest request) {
        try {
            // Get course information
            Optional<Course> courseOpt = courseService.getCourseById(request.getCourseId());
            if (courseOpt.isEmpty()) {
                throw new RuntimeException("Course not found with ID: " + request.getCourseId());
            }

            Course course = courseOpt.get();

            // Initialize OpenAI service
            OpenAiService service = new OpenAiService(openAiApiKey);

            // Create the prompt
            String prompt = buildPrompt(request, course);

            // Create chat messages
            ChatMessage systemMessage = new ChatMessage();
            systemMessage.setRole(ChatMessageRole.SYSTEM.value());
            systemMessage.setContent(getSystemPrompt());

            ChatMessage userMessage = new ChatMessage();
            userMessage.setRole(ChatMessageRole.USER.value());
            userMessage.setContent(prompt);

            // Create chat completion request
            ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(Arrays.asList(systemMessage, userMessage))
                    .maxTokens(2000)
                    .temperature(0.7)
                    .build();

            // Get response from OpenAI
            var completion = service.createChatCompletion(completionRequest);
            String aiResponse = completion.getChoices().get(0).getMessage().getContent();

            // Parse the AI response into questions
            List<GeneratedQuestionDTO> questions = parseAIResponse(aiResponse, request);

            // Build response
            QuestionGenerationResponse response = new QuestionGenerationResponse();
            response.setMessage("Questions generated successfully using AI");
            response.setCourseId(course.getId());
            response.setCourseName(course.getName());
            response.setTopic(request.getTopic());
            response.setQuestions(questions);
            response.setGeneratedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate questions: " + e.getMessage(), e);
        }
    }

    private String getSystemPrompt() {
        return "You are an expert educational content creator. Generate high-quality educational questions " +
               "based on the given topic and requirements. Always respond with valid JSON format containing " +
               "an array of questions. Each question should have: questionText, questionType, difficulty, " +
               "options (for multiple choice), correctAnswer, explanation, and topic fields.";
    }

    private String buildPrompt(QuestionGenerationRequest request, Course course) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("Generate ").append(request.getNumberOfQuestions())
              .append(" ").append(request.getDifficulty()).append(" level ")
              .append(request.getQuestionType().replace("_", " "))
              .append(" questions about '").append(request.getTopic()).append("'");

        prompt.append(" for the course: ").append(course.getName());

        if (course.getDescription() != null && !course.getDescription().isEmpty()) {
            prompt.append(" (").append(course.getDescription()).append(")");
        }

        if (request.getAdditionalContext() != null && !request.getAdditionalContext().isEmpty()) {
            prompt.append("\n\nAdditional context: ").append(request.getAdditionalContext());
        }

        prompt.append("\n\nPlease respond with a JSON object containing a 'questions' array. ");

        if ("multiple_choice".equals(request.getQuestionType())) {
            prompt.append("For multiple choice questions, provide 4 options (A, B, C, D) and specify the correct answer. ");
        }

        prompt.append("Include explanations for each answer. ");
        prompt.append("Format: {\"questions\": [{\"questionText\": \"...\", \"questionType\": \"")
              .append(request.getQuestionType()).append("\", \"difficulty\": \"")
              .append(request.getDifficulty()).append("\", \"options\": [...], \"correctAnswer\": \"...\", ")
              .append("\"explanation\": \"...\", \"topic\": \"").append(request.getTopic()).append("\"}]}");

        return prompt.toString();
    }

    private List<GeneratedQuestionDTO> parseAIResponse(String aiResponse, QuestionGenerationRequest request) {
        List<GeneratedQuestionDTO> questions = new ArrayList<>();

        try {
            // Clean the response to extract JSON
            String jsonResponse = extractJsonFromResponse(aiResponse);

            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode questionsNode = rootNode.get("questions");

            if (questionsNode != null && questionsNode.isArray()) {
                for (JsonNode questionNode : questionsNode) {
                    GeneratedQuestionDTO question = new GeneratedQuestionDTO();

                    question.setQuestionText(questionNode.get("questionText").asText());
                    question.setQuestionType(request.getQuestionType());
                    question.setDifficulty(request.getDifficulty());
                    question.setTopic(request.getTopic());

                    if (questionNode.has("correctAnswer")) {
                        question.setCorrectAnswer(questionNode.get("correctAnswer").asText());
                    }

                    if (questionNode.has("explanation")) {
                        question.setExplanation(questionNode.get("explanation").asText());
                    }

                    // Handle options for multiple choice questions
                    if (questionNode.has("options") && questionNode.get("options").isArray()) {
                        List<String> options = new ArrayList<>();
                        for (JsonNode optionNode : questionNode.get("options")) {
                            options.add(optionNode.asText());
                        }
                        question.setOptions(options);
                    }

                    questions.add(question);
                }
            }

        } catch (JsonProcessingException e) {
            // Fallback: create a simple question if JSON parsing fails
            GeneratedQuestionDTO fallbackQuestion = new GeneratedQuestionDTO();
            fallbackQuestion.setQuestionText("AI generated question about: " + request.getTopic());
            fallbackQuestion.setQuestionType(request.getQuestionType());
            fallbackQuestion.setDifficulty(request.getDifficulty());
            fallbackQuestion.setTopic(request.getTopic());
            fallbackQuestion.setExplanation("This is a fallback question due to parsing error: " + e.getMessage());
            questions.add(fallbackQuestion);
        }

        return questions;
    }

    private String extractJsonFromResponse(String response) {
        // Find JSON block in the response
        int jsonStart = response.indexOf("{");
        int jsonEnd = response.lastIndexOf("}") + 1;

        if (jsonStart >= 0 && jsonEnd > jsonStart) {
            return response.substring(jsonStart, jsonEnd);
        }

        // If no JSON found, return a basic structure
        return "{\"questions\": []}";
    }
}
