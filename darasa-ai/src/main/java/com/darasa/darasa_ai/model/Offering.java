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

    // Constructors
    public Offering() {}

    public Offering(String code, LocalDate startDate, LocalDate endDate,
                   Set<DayOfWeek> daysOfWeek, LocalTime startTime, LocalTime endTime) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Course getCourse() {
        return course;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Set<Timedtest> getTimedTests() {
        return timedTests;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public Note getNote() {
        return note;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setTimedTests(Set<Timedtest> timedTests) {
        this.timedTests = timedTests;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setNote(Note note) {
        this.note = note;
    }

}
