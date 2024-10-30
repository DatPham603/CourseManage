package com.example.QuanLyKhoaHoc.Service.CourseService;

import com.example.QuanLyKhoaHoc.DTO.Request.CourseDTO.CourseRegisterDTO;
import com.example.QuanLyKhoaHoc.Models.Course;
import com.example.QuanLyKhoaHoc.Repository.ICourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService{
    private final ICourseRepository courseRepository;
    @Override
    public Course createCourse(CourseRegisterDTO courseRegisterDTO) {
        Course course = Course.builder()
                .courseName(courseRegisterDTO.getCourseName())
                .description(courseRegisterDTO.getDescription())
                .build();
        return courseRepository.save(course);
    }
    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }
    @Override
    public Course getCoureByCourseId(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
    }
    @Override
    public void deleteCourse(Long courseId) {
        Course course = getCoureByCourseId(courseId);
        courseRepository.delete(course);
    }
}
