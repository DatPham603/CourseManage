package com.project.QuanLyKhoaHoc.Repository;

import com.project.QuanLyKhoaHoc.Models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findByCourseId(Long courseId);
}
