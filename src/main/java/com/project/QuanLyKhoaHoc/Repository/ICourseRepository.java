package com.project.QuanLyKhoaHoc.Repository;

import com.project.QuanLyKhoaHoc.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {
}
