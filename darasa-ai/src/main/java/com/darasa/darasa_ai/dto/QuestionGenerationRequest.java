package com.darasa.darasa_ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

public class QuestionGenerationRequest {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotBlank(message = "Topic is required")
    private String topic;

    @Min(value = 1, message = "Number of questions must be at least 1")
    @Max(value = 20, message = "Number of questions cannot exceed 20")
    private int numberOfQuestions = 5;

    private String difficulty = "medium"; //easy, medium, hard

    private String questionType = "multiple_choice"; //multiple_choice, short_answer, essay

    private String additionalContext;

    // Constructors
    public QuestionGenerationRequest() {}

    public QuestionGenerationRequest(Long courseId, String topic, int numberOfQuestions) {
        this.courseId = courseId;
        this.topic = topic;
        this.numberOfQuestions = numberOfQuestions;
    }

    // Getters
    public Long getCourseId() {
        return courseId;
    }

    public String getTopic() {
        return topic;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getAdditionalContext() {
        return additionalContext;
    }

    // Setters
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setAdditionalContext(String additionalContext) {
        this.additionalContext = additionalContext;
    }
}
