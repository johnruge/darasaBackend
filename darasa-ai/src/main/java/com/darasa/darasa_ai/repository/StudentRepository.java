package com.darasa.darasa_ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darasa.darasa_ai.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByEmail(String email);
    Optional<Student> findByName(String name);
}
