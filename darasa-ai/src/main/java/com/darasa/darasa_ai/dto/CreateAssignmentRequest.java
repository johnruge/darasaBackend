package com.darasa.darasa_ai.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public class CreateAssignmentRequest {

    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be in the future")
    private LocalTime deadline;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Min(value = 0, message = "Max points must be non-negative")
    private int maxPoints;

    public CreateAssignmentRequest() {}

    public CreateAssignmentRequest(LocalTime deadline, String description, int maxPoints) {
        this.deadline = deadline;
        this.description = description;
        this.maxPoints = maxPoints;
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
}