package com.darasa.darasa_ai.dto;

import java.util.List;

public class QuestionGenerationResponse {

    private String message;
    private Long courseId;
    private String courseName;
    private String topic;
    private int totalQuestions;
    private List<GeneratedQuestionDTO> questions;
    private String generatedAt;

    // Constructors
    public QuestionGenerationResponse() {}

    public QuestionGenerationResponse(String message, List<GeneratedQuestionDTO> questions) {
        this.message = message;
        this.questions = questions;
        this.totalQuestions = questions != null ? questions.size() : 0;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTopic() {
        return topic;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public List<GeneratedQuestionDTO> getQuestions() {
        return questions;
    }

    public String getGeneratedAt() {
        return generatedAt;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setQuestions(List<GeneratedQuestionDTO> questions) {
        this.questions = questions;
        this.totalQuestions = questions != null ? questions.size() : 0;
    }

    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
}
