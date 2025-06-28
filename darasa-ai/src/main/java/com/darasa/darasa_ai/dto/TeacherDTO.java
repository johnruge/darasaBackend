package com.darasa.darasa_ai.dto;

public class TeacherDTO {
    private Long id;
    private String username;
    private String fullname;
    private String preferredName;
    private String email;

    public TeacherDTO() {}

    public TeacherDTO(Long id, String username, String fullname, String preferredName, String email) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.preferredName = preferredName;
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

    public String getPreferredName() {
        return preferredName;
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

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}