package com.project.QuanLyKhoaHoc.Service.EnrollmentService;

import com.project.QuanLyKhoaHoc.DTO.Request.EnrollDTO.EnrollRegisterDTO;
import com.project.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.project.QuanLyKhoaHoc.Models.Course;
import com.project.QuanLyKhoaHoc.Models.Enrollment;
import com.project.QuanLyKhoaHoc.Models.User;
import com.project.QuanLyKhoaHoc.Repository.ICourseRepository;
import com.project.QuanLyKhoaHoc.Repository.IEnrollmentRepository;
import com.project.QuanLyKhoaHoc.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService implements IEnrollmentService{
    private final IEnrollmentRepository enrollmentRepository;
    private final IUserRepository userRepository;
    private final ICourseRepository courseRepository;

    @Override
    public Enrollment createEnrollment(EnrollRegisterDTO enrollmentDTO) throws DataNotFoundException {
        User user = userRepository.findById(enrollmentDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .build();
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long enrollmentId) throws DataNotFoundException {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new DataNotFoundException("Enrollment not found"));
    }

    @Override
    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }
}
