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
}
