package com.darasa.darasa_ai.dto;

public class StudentDTO {
    private Long id;
    private String username;
    private String fullname;
    private String preferredname;
    private String email;

    public StudentDTO() {}

    public StudentDTO(Long id, String username, String fullname, String preferredname, String email) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.preferredname = preferredname;
        this.email = email;
    }

    // Getters
    public Long getId() {
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

    // Setters
    public void setId(Long id) {
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
}