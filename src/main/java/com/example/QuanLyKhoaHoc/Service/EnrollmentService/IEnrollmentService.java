package com.example.QuanLyKhoaHoc.Service.EnrollmentService;

import com.example.QuanLyKhoaHoc.DTO.Request.EnrollDTO.EnrollRegisterDTO;
import com.example.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.example.QuanLyKhoaHoc.Models.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    Enrollment createEnrollment(EnrollRegisterDTO enrollmentDTO) throws DataNotFoundException;
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long enrollmentId) throws DataNotFoundException;
    List<Enrollment> getEnrollmentsByUserId(Long userId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
    void deleteEnrollment(Long enrollmentId);
}
