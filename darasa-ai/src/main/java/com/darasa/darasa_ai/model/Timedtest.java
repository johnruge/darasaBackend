package com.darasa.darasa_ai.model;

import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Timedtest {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private LocalTime createdTime;
    @Column
    private LocalTime deadline;
    @Column
    private int duration;
    @Column
    private String description;
    @Column
    private boolean isCompleted;
    @Column
    private boolean isActive;
    @Column
    private LocalTime submissionTime;
    @Column
    private int points;
    @Column
    private int maxPoints;

    @ManyToOne
    @JoinColumn(name = "offering_id")
    private Offering offering;

    @OneToMany(mappedBy = "timedtest", cascade = CascadeType.ALL)
    private Set<Question> questions;
}
