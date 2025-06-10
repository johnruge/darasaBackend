package com.darasa.darasa_ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darasa.darasa_ai.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByFullname(String fullname);
    Optional<Teacher> findByUsername(String username);
}
