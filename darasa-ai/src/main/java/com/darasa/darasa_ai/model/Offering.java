package com.darasa.darasa_ai.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Offering {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String code;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "course_days", joinColumns =
    @JoinColumn(name = "course_offering_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private Set<DayOfWeek> daysOfWeek;

    @Column
    private LocalTime startTime;
    @Column
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToMany(mappedBy = "offerings")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "offering", cascade = CascadeType.ALL)
    private Set<Timedtest> timedTests;

    @OneToMany(mappedBy = "offering", cascade = CascadeType.ALL)
    private Set<Assignment> assignments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notes_id", referencedColumnName = "id")
    private Note note;

}
