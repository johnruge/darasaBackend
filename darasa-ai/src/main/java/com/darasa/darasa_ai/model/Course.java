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

    // Constructors
    public Course() {}

    public Course(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Set<Offering> getOfferings() {
        return offerings;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setOfferings(Set<Offering> offerings) {
        this.offerings = offerings;
    }
}
