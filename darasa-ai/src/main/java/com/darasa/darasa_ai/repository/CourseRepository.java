package com.darasa.darasa_ai.repository;

import com.darasa.darasa_ai.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    //no custom methods
}
