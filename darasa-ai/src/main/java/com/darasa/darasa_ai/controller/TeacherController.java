package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.dto.TeacherDTO;
import com.darasa.darasa_ai.dto.CreateTeacherRequest;
import com.darasa.darasa_ai.dto.UpdateTeacherRequest;
import com.darasa.darasa_ai.model.Teacher;
import com.darasa.darasa_ai.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDTO> teacherDTOs = teachers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Long id) {
        return teacherService.getTeacherById(id)
                .map(teacher -> ResponseEntity.ok(convertToDTO(teacher)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid CreateTeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setUsername(request.getUsername());
        teacher.setFullname(request.getFullname());
        teacher.setPreferredName(request.getPreferredName());
        teacher.setEmail(request.getEmail());

        Teacher savedTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedTeacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateTeacherRequest request) {
        return teacherService.getTeacherById(id)
                .map(existingTeacher -> {
                    existingTeacher.setUsername(request.getUsername());
                    existingTeacher.setFullname(request.getFullname());
                    existingTeacher.setPreferredName(request.getPreferredName());
                    existingTeacher.setEmail(request.getEmail());
                    Teacher updatedTeacher = teacherService.createTeacher(existingTeacher);
                    return ResponseEntity.ok(convertToDTO(updatedTeacher));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        if (teacherService.getTeacherById(id).isPresent()) {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(teacher.getId());
        dto.setUsername(teacher.getUsername());
        dto.setFullname(teacher.getFullname());
        dto.setPreferredName(teacher.getPreferredName());
        dto.setEmail(teacher.getEmail());
        return dto;
    }
}