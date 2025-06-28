package com.darasa.darasa_ai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTeacherRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name cannot exceed 100 characters")
    private String fullname;

    @Size(max = 50, message = "Preferred name cannot exceed 50 characters")
    private String preferredName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    public CreateTeacherRequest() {}

    public CreateTeacherRequest(String username, String fullname, String preferredName, String email) {
        this.username = username;
        this.fullname = fullname;
        this.preferredName = preferredName;
        this.email = email;
    }

    // Getters
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

    // Setters
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
}