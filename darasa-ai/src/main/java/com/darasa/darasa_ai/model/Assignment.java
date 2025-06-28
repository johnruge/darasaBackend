package com.darasa.darasa_ai.model;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assignment {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private LocalTime createdTime;
    @Column
    private LocalTime deadline;
    @Column
    private String description;
    @Column
    private boolean isCompleted;
    @Column
    private String submissionUrl;
    @Column
    private LocalTime submissionTime;
    @Column
    private int points;
    @Column
    private int maxPoints;
    @Column
    private boolean isGraded;

    @ManyToOne
    @JoinColumn(name = "offering_id")
    private Offering offering;

    // Constructors
    public Assignment() {}

    public Assignment(String description, LocalTime deadline, int maxPoints) {
        this.description = description;
        this.deadline = deadline;
        this.maxPoints = maxPoints;
        this.createdTime = LocalTime.now();
        this.isCompleted = false;
        this.isGraded = false;
        this.points = 0;
    }

    // Getters
    public long getId() {
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

    public Offering getOffering() {
        return offering;
    }

    // Setters
    public void setId(long id) {
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

    public void setOffering(Offering offering) {
        this.offering = offering;
    }
}
