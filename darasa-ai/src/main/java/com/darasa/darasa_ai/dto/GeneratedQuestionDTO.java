package com.darasa.darasa_ai.dto;

import java.util.List;

public class GeneratedQuestionDTO {

    private String questionText;
    private String questionType;
    private String difficulty;
    private List<String> options; //for multiple choice questions
    private String correctAnswer;
    private String explanation;
    private String topic;

    // Constructors
    public GeneratedQuestionDTO() {}

    public GeneratedQuestionDTO(String questionText, String questionType, String difficulty) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.difficulty = difficulty;
    }

    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getTopic() {
        return topic;
    }

    // Setters
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
