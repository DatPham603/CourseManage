package com.example.QuanLyKhoaHoc.Service.CourseService;

import com.example.QuanLyKhoaHoc.DTO.Request.CourseDTO.CourseRegisterDTO;
import com.example.QuanLyKhoaHoc.Models.Course;

import java.util.List;

public interface ICourseService {
    Course createCourse(CourseRegisterDTO courseDTO);
    List<Course> getAllCourse();
    Course getCoureByCourseId(Long courseId);
    void deleteCourse(Long courseId);
}
