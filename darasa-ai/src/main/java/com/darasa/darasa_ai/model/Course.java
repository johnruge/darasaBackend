package com.darasa.darasa_ai.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String code;
    @Column
    private String name;
    @Column
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Offering> offerings;
}
