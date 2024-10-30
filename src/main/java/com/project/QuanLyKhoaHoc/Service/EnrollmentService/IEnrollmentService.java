package com.project.QuanLyKhoaHoc.Service.EnrollmentService;

import com.project.QuanLyKhoaHoc.DTO.Request.EnrollDTO.EnrollRegisterDTO;
import com.project.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.project.QuanLyKhoaHoc.Models.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    Enrollment createEnrollment(EnrollRegisterDTO enrollmentDTO) throws DataNotFoundException;
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(Long enrollmentId) throws DataNotFoundException;
    List<Enrollment> getEnrollmentsByUserId(Long userId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
    void deleteEnrollment(Long enrollmentId);
}
