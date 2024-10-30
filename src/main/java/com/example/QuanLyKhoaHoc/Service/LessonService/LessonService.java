package com.example.QuanLyKhoaHoc.Service.LessonService;

import com.example.QuanLyKhoaHoc.DTO.Request.LessonDTO.LessonCreateDTO;
import com.example.QuanLyKhoaHoc.DTO.Request.LessonDTO.UpdateLesson;
import com.example.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.example.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.example.QuanLyKhoaHoc.Models.Course;
import com.example.QuanLyKhoaHoc.Models.Lesson;
import com.example.QuanLyKhoaHoc.Models.Video;
import com.example.QuanLyKhoaHoc.Repository.ICourseRepository;
import com.example.QuanLyKhoaHoc.Repository.ILessonRepository;
import com.example.QuanLyKhoaHoc.Repository.IVideoRepository;
import com.example.QuanLyKhoaHoc.Service.VideoServce.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService{
    private final ILessonRepository lessonRepository;
    private final ICourseRepository courseRepository;
    private final IVideoRepository videoRepository;
    private final VideoService videoService;
    @Override
    public Lesson createLesson(LessonCreateDTO lessonCreateDTO, VideoDTO videoDTO, MultipartFile videoFile)
            throws DataNotFoundException, IOException {
        Course course = courseRepository.findById(lessonCreateDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        Lesson lesson = Lesson.builder()
                .lessonTitle(lessonCreateDTO.getLessonTitle())
                .content(lessonCreateDTO.getContent())
                .course(course)
                .build();
        if (videoFile != null && !videoFile.isEmpty()) {
            Video video = videoService.uploadVideo(videoFile, videoDTO);
            lesson.setVideo(video);
        }
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLessonByLessonId(Long lessonId) throws DataNotFoundException {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Lesson not found"));
    }

    @Override
    public List<Lesson> getLessonsByCourse(Long cousreId) {
        return lessonRepository.findByCourseId(cousreId);
    }
    @Override
    public void deleteLessos(Long lessonId) throws DataNotFoundException {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Lesson not found"));
        lessonRepository.delete(lesson);
    }

    @Override
    public Lesson updateLesson(Long lessonId, UpdateLesson updateLessonDTO) throws DataNotFoundException {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new DataNotFoundException("Lesson not found"));
        lesson.setLessonTitle(updateLessonDTO.getLessonTitle());
        lesson.setContent(updateLessonDTO.getContent());
        Course course = courseRepository.findById(updateLessonDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        lesson.setCourse(course);
        if (updateLessonDTO.getVideoId() != null) {
            Video video = videoRepository.findById(updateLessonDTO.getVideoId())
                    .orElseThrow(() -> new DataNotFoundException("Video not found"));
            lesson.setVideo(video);
        }
        return lessonRepository.save(lesson);
    }
}
