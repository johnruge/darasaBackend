package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.dto.AssignmentDTO;
import com.darasa.darasa_ai.dto.CreateAssignmentRequest;
import com.darasa.darasa_ai.dto.UpdateAssignmentRequest;
import com.darasa.darasa_ai.model.Assignment;
import com.darasa.darasa_ai.services.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        List<AssignmentDTO> assignmentDTOs = assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(assignmentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentDTO> getAssignment(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id)
                .map(assignment -> ResponseEntity.ok(convertToDTO(assignment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody @Valid CreateAssignmentRequest request) {
        Assignment assignment = new Assignment();
        assignment.setDescription(request.getDescription());
        assignment.setDeadline(request.getDeadline());
        assignment.setMaxPoints(request.getMaxPoints());

        Assignment savedAssignment = assignmentService.createAssignment(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAssignment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentDTO> updateAssignment(@PathVariable Long id,
                                                         @RequestBody @Valid UpdateAssignmentRequest request) {
        return assignmentService.getAssignmentById(id)
                .map(existingAssignment -> {
                    existingAssignment.setDescription(request.getDescription());
                    existingAssignment.setDeadline(request.getDeadline());
                    existingAssignment.setMaxPoints(request.getMaxPoints());
                    existingAssignment.setCompleted(request.isCompleted());
                    existingAssignment.setSubmissionUrl(request.getSubmissionUrl());
                    existingAssignment.setPoints(request.getPoints());
                    existingAssignment.setGraded(request.isGraded());
                    Assignment updatedAssignment = assignmentService.createAssignment(existingAssignment);
                    return ResponseEntity.ok(convertToDTO(updatedAssignment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        if (assignmentService.getAssignmentById(id).isPresent()) {
            assignmentService.deleteAssignment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/submit")
    public ResponseEntity<AssignmentDTO> submitAssignment(@PathVariable Long id,
                                                         @RequestParam String submissionUrl) {
        return assignmentService.getAssignmentById(id)
                .map(assignment -> {
                    assignment.setSubmissionUrl(submissionUrl);
                    assignment.setCompleted(true);
                    assignment.setSubmissionTime(java.time.LocalTime.now());
                    Assignment updatedAssignment = assignmentService.createAssignment(assignment);
                    return ResponseEntity.ok(convertToDTO(updatedAssignment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/grade")
    public ResponseEntity<AssignmentDTO> gradeAssignment(@PathVariable Long id,
                                                        @RequestParam int points) {
        return assignmentService.getAssignmentById(id)
                .map(assignment -> {
                    assignment.setPoints(points);
                    assignment.setGraded(true);
                    Assignment updatedAssignment = assignmentService.createAssignment(assignment);
                    return ResponseEntity.ok(convertToDTO(updatedAssignment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId());
        dto.setCreatedTime(assignment.getCreatedTime());
        dto.setDeadline(assignment.getDeadline());
        dto.setDescription(assignment.getDescription());
        dto.setCompleted(assignment.isCompleted());
        dto.setSubmissionUrl(assignment.getSubmissionUrl());
        dto.setSubmissionTime(assignment.getSubmissionTime());
        dto.setPoints(assignment.getPoints());
        dto.setMaxPoints(assignment.getMaxPoints());
        dto.setGraded(assignment.isGraded());
        return dto;
    }
}