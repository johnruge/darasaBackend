package com.darasa.darasa_ai.controller;

import com.darasa.darasa_ai.dto.CourseDTO;
import com.darasa.darasa_ai.dto.CreateCourseRequest;
import com.darasa.darasa_ai.dto.QuestionGenerationRequest;
import com.darasa.darasa_ai.dto.QuestionGenerationResponse;
import com.darasa.darasa_ai.dto.UpdateCourseRequest;
import com.darasa.darasa_ai.model.Course;
import com.darasa.darasa_ai.services.AIService;
import com.darasa.darasa_ai.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;
    private final AIService aiService;

    public CourseController(CourseService courseService, AIService aiService) {
        this.courseService = courseService;
        this.aiService = aiService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> courseDTOs = courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(course -> ResponseEntity.ok(convertToDTO(course)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CreateCourseRequest request) {
        Course course = new Course();
        course.setCode(request.getCode());
        course.setName(request.getName());
        course.setDescription(request.getDescription());

        Course savedCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedCourse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id,
                                                  @RequestBody @Valid UpdateCourseRequest request) {
        return courseService.getCourseById(id)
                .map(existingCourse -> {
                    existingCourse.setCode(request.getCode());
                    existingCourse.setName(request.getName());
                    existingCourse.setDescription(request.getDescription());
                    Course updatedCourse = courseService.createCourse(existingCourse);
                    return ResponseEntity.ok(convertToDTO(updatedCourse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseService.getCourseById(id).isPresent()) {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // AI-Powered Question Generation Endpoint
    @PostMapping("/{id}/generate-questions")
    public ResponseEntity<QuestionGenerationResponse> generateQuestions(
            @PathVariable Long id,
            @RequestBody @Valid QuestionGenerationRequest request) {

        try {
            // Ensure the course ID matches the path parameter
            request.setCourseId(id);

            // Validate course exists
            if (courseService.getCourseById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Generate questions using AI
            QuestionGenerationResponse response = aiService.generateQuestions(request);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Return error response
            QuestionGenerationResponse errorResponse = new QuestionGenerationResponse();
            errorResponse.setMessage("Failed to generate questions: " + e.getMessage());
            errorResponse.setCourseId(id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setCode(course.getCode());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        return dto;
    }
}