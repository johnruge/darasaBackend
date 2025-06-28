package com.darasa.darasa_ai.model;
import java.util.Set;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;
    @Column
    private String fullname;
    @Column
    private String preferredName;
    @Column
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "teacher_courses",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Offering> offerings;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Note> notes;

    // Constructors
    public Teacher() {}

    public Teacher(String username, String fullname, String preferredName, String email) {
        this.username = username;
        this.fullname = fullname;
        this.preferredName = preferredName;
        this.email = email;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Set<Offering> getOfferings() {
        return offerings;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void setOfferings(Set<Offering> offerings) {
        this.offerings = offerings;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
}
