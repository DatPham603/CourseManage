package com.project.QuanLyKhoaHoc.Controller;

import com.project.QuanLyKhoaHoc.DTO.Request.LessonDTO.LessonCreateDTO;
import com.project.QuanLyKhoaHoc.DTO.Request.LessonDTO.UpdateLesson;
import com.project.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.project.QuanLyKhoaHoc.DTO.Response.ApiResponse;
import com.project.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.project.QuanLyKhoaHoc.Models.Lesson;
import com.project.QuanLyKhoaHoc.Service.LessonService.ILessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final ILessonService lessonService;

    @PostMapping
    public ResponseEntity<ApiResponse<Lesson>> createLesson(
            @RequestParam("lesson") LessonCreateDTO lessonCreateDTO,
            @RequestParam("video") VideoDTO videoDTO,
            @RequestParam("videoFile") MultipartFile videoFile) {
        try {
            Lesson lesson = lessonService.createLesson(lessonCreateDTO, videoDTO, videoFile);
            ApiResponse<Lesson> response = ApiResponse.<Lesson>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("Lesson created successfully")
                    .result(lesson)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Lesson> response = ApiResponse.<Lesson>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<Lesson>> getLessonById(@PathVariable Long lessonId) {
        try {
            Lesson lesson = lessonService.getLessonByLessonId(lessonId);
            ApiResponse<Lesson> response = ApiResponse.<Lesson>builder()
                    .code(HttpStatus.OK.value())
                    .message("Lesson retrieved successfully")
                    .result(lesson)
                    .build();
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            ApiResponse<Lesson> response = ApiResponse.<Lesson>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Lesson>>> getLessonsByCourse(@RequestParam Long courseId) {
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);
        ApiResponse<List<Lesson>> response = ApiResponse.<List<Lesson>>builder()
                .code(HttpStatus.OK.value())
                .message("Lessons retrieved successfully")
                .result(lessons)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<Lesson>> updateLesson(
            @PathVariable Long lessonId,
            @RequestBody UpdateLesson updateLessonDTO) {
        try {
            Lesson updatedLesson = lessonService.updateLesson(lessonId, updateLessonDTO);
            ApiResponse<Lesson> response = ApiResponse.<Lesson>builder()
                    .code(HttpStatus.OK.value())
                    .message("Lesson updated successfully")
                    .result(updatedLesson)
                    .build();
            return ResponseEntity.ok(response);
        } catch (DataNotFoundException e) {
            ApiResponse<Lesson> response = ApiResponse.<Lesson>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<ApiResponse<Void>> deleteLesson(@PathVariable Long lessonId) {
        try {
            lessonService.deleteLessos(lessonId);
            ApiResponse<Void> response = ApiResponse.<Void>builder()
                    .code(HttpStatus.NO_CONTENT.value())
                    .message("Lesson deleted successfully")
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (DataNotFoundException e) {
            ApiResponse<Void> response = ApiResponse.<Void>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}