package com.darasa.darasa_ai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name cannot exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Signup code is required")
    private String signupCode;

    // Optional role field for admin creation
    private String role = "ROLE_USER";

    public SignupRequest() {}

    public SignupRequest(String email, String password, String fullName, String signupCode) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.signupCode = signupCode;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSignupCode() {
        return signupCode;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSignupCode(String signupCode) {
        this.signupCode = signupCode;
    }

    public void setRole(String role) {
        this.role = role;
    }
}