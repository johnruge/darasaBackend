package com.darasa.darasa_ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class CreateOfferingRequest {

    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code cannot exceed 10 characters")
    private String code;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Days of week are required")
    @Size(min = 1, message = "At least one day of the week must be selected")
    private Set<String> daysOfWeek;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    public CreateOfferingRequest() {}

    public CreateOfferingRequest(String code, LocalDate startDate, LocalDate endDate,
                                Set<String> daysOfWeek, LocalTime startTime, LocalTime endTime) {
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysOfWeek = daysOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
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
        return code;
    }

    public Integer getYear() {
        return startDate != null ? startDate.getYear() : null;
    }

    public String getSection() {
        return code;
    }

    // Legacy setters for backwards compatibility (removing these)
    public void setSemester(String semester) {
        this.code = semester;
    }

    public void setYear(Integer year) {
        // For backwards compatibility, but should be removed
    }

    public void setSection(String section) {
        this.code = section;
    }
}