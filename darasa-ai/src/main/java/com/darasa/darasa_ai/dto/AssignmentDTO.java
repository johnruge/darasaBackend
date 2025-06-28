package com.darasa.darasa_ai.dto;

import java.time.LocalTime;

public class AssignmentDTO {
    private Long id;
    private LocalTime createdTime;
    private LocalTime deadline;
    private String description;
    private boolean isCompleted;
    private String submissionUrl;
    private LocalTime submissionTime;
    private int points;
    private int maxPoints;
    private boolean isGraded;

    public AssignmentDTO() {}

    public AssignmentDTO(Long id, LocalTime createdTime, LocalTime deadline, String description,
                        boolean isCompleted, String submissionUrl, LocalTime submissionTime,
                        int points, int maxPoints, boolean isGraded) {
        this.id = id;
        this.createdTime = createdTime;
        this.deadline = deadline;
        this.description = description;
        this.isCompleted = isCompleted;
        this.submissionUrl = submissionUrl;
        this.submissionTime = submissionTime;
        this.points = points;
        this.maxPoints = maxPoints;
        this.isGraded = isGraded;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public LocalTime getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getSubmissionUrl() {
        return submissionUrl;
    }

    public LocalTime getSubmissionTime() {
        return submissionTime;
    }

    public int getPoints() {
        return points;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public boolean isGraded() {
        return isGraded;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public void setDeadline(LocalTime deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setSubmissionUrl(String submissionUrl) {
        this.submissionUrl = submissionUrl;
    }

    public void setSubmissionTime(LocalTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }
}