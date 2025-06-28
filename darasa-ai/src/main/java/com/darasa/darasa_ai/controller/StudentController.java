package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.dto.StudentDTO;
import com.darasa.darasa_ai.dto.CreateStudentRequest;
import com.darasa.darasa_ai.dto.UpdateStudentRequest;
import com.darasa.darasa_ai.model.Student;
import com.darasa.darasa_ai.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDTO> studentDTOs = students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok(convertToDTO(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid CreateStudentRequest request) {
        Student student = new Student();
        student.setUsername(request.getUsername());
        student.setFullname(request.getFullname());
        student.setPreferredname(request.getPreferredname());
        student.setEmail(request.getEmail());

        Student savedStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedStudent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,
                                                   @RequestBody @Valid UpdateStudentRequest request) {
        return studentService.getStudentById(id)
                .map(existingStudent -> {
                    existingStudent.setUsername(request.getUsername());
                    existingStudent.setFullname(request.getFullname());
                    existingStudent.setPreferredname(request.getPreferredname());
                    existingStudent.setEmail(request.getEmail());
                    Student updatedStudent = studentService.createStudent(existingStudent);
                    return ResponseEntity.ok(convertToDTO(updatedStudent));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.getStudentById(id).isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setUsername(student.getUsername());
        dto.setFullname(student.getFullname());
        dto.setPreferredname(student.getPreferredname());
        dto.setEmail(student.getEmail());
        return dto;
    }
}