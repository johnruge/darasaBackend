package com.darasa.darasa_ai.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;
    @Column
    private LocalTime createdTime;
    @Column
    private LocalTime lastUpdated;
    @Column
    private String description;
    @Column
    private String body;

    @OneToOne(mappedBy = "note")
    private Offering offering;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
