package com.darasa.darasa_ai.repository;

import com.darasa.darasa_ai.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    //no custom methods
}
