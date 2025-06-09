package com.darasa.darasa_ai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String body; //question

    @Column
    private String choices;

    @Column
    private String ans; //should be a letter

    @ManyToOne
    @JoinColumn(name = "timedtest_id")
    private Timedtest timedtest;
}
