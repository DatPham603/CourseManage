package com.project.QuanLyKhoaHoc.Controller;

import com.project.QuanLyKhoaHoc.DTO.Request.EnrollDTO.EnrollRegisterDTO;
import com.project.QuanLyKhoaHoc.DTO.Response.ApiResponse;
import com.project.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.project.QuanLyKhoaHoc.Models.Enrollment;
import com.project.QuanLyKhoaHoc.Service.EnrollmentService.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final IEnrollmentService enrollmentService;
    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody EnrollRegisterDTO enrollmentDTO) throws DataNotFoundException {
        Enrollment enrollment = enrollmentService.createEnrollment(enrollmentDTO);
        ApiResponse<Enrollment> response = ApiResponse.<Enrollment>builder()
                .code(HttpStatus.CREATED.value())
                .message("Enrollment created successfully")
                .result(enrollment)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{enrollmentId}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable Long enrollmentId) {
        try {
            Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
            ApiResponse<Enrollment> response = ApiResponse.<Enrollment>builder()
                    .code(HttpStatus.OK.value())
                    .message("Enrollment retrieved successfully")
                    .result(enrollment)
                    .build();
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            ApiResponse<Enrollment> response = ApiResponse.<Enrollment>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        ApiResponse<List<Enrollment>> response = ApiResponse.<List<Enrollment>>builder()
                .code(HttpStatus.OK.value())
                .message("All enrollments retrieved successfully")
                .result(enrollments)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getEnrollmentsByUserId(@PathVariable Long userId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUserId(userId);
        ApiResponse<List<Enrollment>> response = ApiResponse.<List<Enrollment>>builder()
                .code(HttpStatus.OK.value())
                .message("Enrollments for user retrieved successfully")
                .result(enrollments)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
        ApiResponse<List<Enrollment>> response = ApiResponse.<List<Enrollment>>builder()
                .code(HttpStatus.OK.value())
                .message("Enrollments for course retrieved successfully")
                .result(enrollments)
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Enrollment deleted successfully")
                .result(null)
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}