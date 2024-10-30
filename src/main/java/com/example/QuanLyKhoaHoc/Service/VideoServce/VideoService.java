package com.example.QuanLyKhoaHoc.Service.VideoServce;

import com.example.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.example.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.example.QuanLyKhoaHoc.Models.Video;
import com.example.QuanLyKhoaHoc.Repository.IVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService{
    private final IVideoRepository videoRepository;
    private final String uploadDir = "uploads/videos/";
    @Override
    public Video uploadVideo(MultipartFile file, VideoDTO videoDTO) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = file.getOriginalFilename();
        if(fileName == null){
            throw new IllegalArgumentException("Tên file không hợp lệ.");
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        Video video = Video.builder()
                .title(videoDTO.getTitle())
                .url(filePath.toString())
                .duration(videoDTO.getDuration())
                .lessonId(videoDTO.getLessonId())
                .uploadedAt(LocalDateTime.now())
                .build();
        return videoRepository.save(video);
    }
    public Video getVideoById(Long videoId) throws DataNotFoundException {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new DataNotFoundException("Video not found with id: " + videoId));
    }
}
