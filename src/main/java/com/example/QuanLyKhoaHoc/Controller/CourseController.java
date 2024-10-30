package com.example.QuanLyKhoaHoc.Controller;

import com.example.QuanLyKhoaHoc.DTO.Request.CourseDTO.CourseRegisterDTO;
import com.example.QuanLyKhoaHoc.DTO.Response.ApiResponse;
import com.example.QuanLyKhoaHoc.Models.Course;
import com.example.QuanLyKhoaHoc.Service.CourseService.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService courseService;
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseRegisterDTO courseRegisterDTO) {
        Course createdCourse = courseService.createCourse(courseRegisterDTO);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .code(HttpStatus.CREATED.value())
                .message("Course created successfully")
                .result(createdCourse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourse();
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .code(HttpStatus.OK.value())
                .message("Retrieved all courses successfully")
                .result(courses)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("id") Long courseId) {
        Course course = courseService.getCoureByCourseId(courseId);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .code(HttpStatus.OK.value())
                .message("Course retrieved successfully")
                .result(course)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Course deleted successfully")
                .result(null)
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}