package com.project.QuanLyKhoaHoc.Service.CourseService;

import com.project.QuanLyKhoaHoc.DTO.Request.CourseDTO.CourseRegisterDTO;
import com.project.QuanLyKhoaHoc.Models.Course;

import java.util.List;

public interface ICourseService {
    Course createCourse(CourseRegisterDTO courseDTO);
    List<Course> getAllCourse();
    Course getCoureByCourseId(Long courseId);
    void deleteCourse(Long courseId);
}
