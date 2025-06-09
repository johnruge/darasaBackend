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
}
