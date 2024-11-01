package com.project.QuanLyKhoaHoc.Service.LessonService;

import com.project.QuanLyKhoaHoc.DTO.Request.LessonDTO.LessonCreateDTO;
import com.project.QuanLyKhoaHoc.DTO.Request.LessonDTO.UpdateLesson;
import com.project.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.project.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.project.QuanLyKhoaHoc.Models.Lesson;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ILessonService {
    public Lesson createLesson(LessonCreateDTO lessonCreateDTO, VideoDTO videoDTO, MultipartFile videoFile)
            throws DataNotFoundException, IOException;
    Lesson getLessonByLessonId(Long lessonId) throws DataNotFoundException;
    List<Lesson> getLessonsByCourse(Long cousreId);
    void deleteLessos(Long lessonId) throws DataNotFoundException;
    Lesson updateLesson(Long lessonId, UpdateLesson updateLessonDTO) throws DataNotFoundException;
}
