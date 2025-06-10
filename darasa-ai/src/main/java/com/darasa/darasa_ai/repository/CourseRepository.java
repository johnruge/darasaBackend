package com.darasa.darasa_ai.repository;

import com.darasa.darasa_ai.model.Course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);
    Optional<Course> findByName(String name);
}
