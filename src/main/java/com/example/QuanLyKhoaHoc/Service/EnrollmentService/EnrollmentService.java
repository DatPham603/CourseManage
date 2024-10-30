package com.example.QuanLyKhoaHoc.Service.EnrollmentService;

import com.example.QuanLyKhoaHoc.DTO.Request.EnrollDTO.EnrollRegisterDTO;
import com.example.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.example.QuanLyKhoaHoc.Models.Course;
import com.example.QuanLyKhoaHoc.Models.Enrollment;
import com.example.QuanLyKhoaHoc.Models.User;
import com.example.QuanLyKhoaHoc.Repository.ICourseRepository;
import com.example.QuanLyKhoaHoc.Repository.IEnrollmentRepository;
import com.example.QuanLyKhoaHoc.Repository.IUserRepository;
import lombok.NoArgsConstructor;
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
