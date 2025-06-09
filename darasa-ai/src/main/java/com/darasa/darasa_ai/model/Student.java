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
}
