package com.darasa.darasa_ai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public class UpdateAssignmentRequest {

    @NotNull(message = "Deadline is required")
    private LocalTime deadline;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Min(value = 0, message = "Max points must be non-negative")
    private int maxPoints;

    private boolean isCompleted;

    @Size(max = 255, message = "Submission URL cannot exceed 255 characters")
    private String submissionUrl;

    @Min(value = 0, message = "Points must be non-negative")
    private int points;

    private boolean isGraded;

    public UpdateAssignmentRequest() {}

    public UpdateAssignmentRequest(LocalTime deadline, String description, int maxPoints,
                                  boolean isCompleted, String submissionUrl, int points, boolean isGraded) {
        this.deadline = deadline;
        this.description = description;
        this.maxPoints = maxPoints;
        this.isCompleted = isCompleted;
        this.submissionUrl = submissionUrl;
        this.points = points;
        this.isGraded = isGraded;
    }

    // Getters
    public LocalTime getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String getSubmissionUrl() {
        return submissionUrl;
    }

    public int getPoints() {
        return points;
    }

    public boolean isGraded() {
        return isGraded;
    }

    // Setters
    public void setDeadline(LocalTime deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setSubmissionUrl(String submissionUrl) {
        this.submissionUrl = submissionUrl;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }
}