package com.darasa.darasa_ai.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class OfferingDTO {
    private Long id;
    private String code;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<String> daysOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public OfferingDTO() {}

    public OfferingDTO(Long id, String code, LocalDate startDate, LocalDate endDate,
                      Set<String> daysOfWeek, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public Long getId() {
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

    public Set<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    // Setters
    public void setId(Long id) {
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

    public void setDaysOfWeek(Set<String> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // Legacy getters for backwards compatibility (removing these)
    public String getSemester() {
        return code; // Mapping to code for now
    }

    public Integer getYear() {
        return startDate != null ? startDate.getYear() : null;
    }

    public String getSection() {
        return code; // Mapping to code for now
    }

    // Legacy setters for backwards compatibility (removing these)
    public void setSemester(String semester) {
        this.code = semester;
    }

    public void setYear(Integer year) {
        // For backwards compatibility, but ideally should be removed
    }

    public void setSection(String section) {
        this.code = section;
    }
}