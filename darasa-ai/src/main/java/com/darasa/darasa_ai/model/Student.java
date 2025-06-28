package com.darasa.darasa_ai.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;
    @Column
    private String fullname;
    @Column
    private String preferredname;
    @Column
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Note> notes;

    @ManyToMany
    @JoinTable(
        name = "student_offering",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "offering_id")
    )
    private Set<Offering> offerings;

    // Constructors
    public Student() {}

    public Student(String username, String fullname, String preferredname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.preferredname = preferredname;
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

    public String getPreferredname() {
        return preferredname;
    }

    public String getEmail() {
        return email;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public Set<Offering> getOfferings() {
        return offerings;
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

    public void setPreferredname(String preferredname) {
        this.preferredname = preferredname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public void setOfferings(Set<Offering> offerings) {
        this.offerings = offerings;
    }
}
