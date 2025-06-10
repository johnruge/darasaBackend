package com.darasa.darasa_ai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darasa.darasa_ai.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByName(String name);
}
