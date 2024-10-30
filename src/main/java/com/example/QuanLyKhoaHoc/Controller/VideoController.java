package com.example.QuanLyKhoaHoc.Controller;

import com.example.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.example.QuanLyKhoaHoc.DTO.Response.ApiResponse;
import com.example.QuanLyKhoaHoc.Models.Video;
import com.example.QuanLyKhoaHoc.Service.VideoServce.IVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final IVideoService videoService;
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Video>> uploadVideo(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("videoDTO") VideoDTO videoDTO) {
        try {
            Video video = videoService.uploadVideo(file, videoDTO);
            ApiResponse<Video> response = ApiResponse.<Video>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("Video uploaded successfully")
                    .result(video)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Video> response = ApiResponse.<Video>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<ApiResponse<Video>> getVideoById(@PathVariable Long videoId) {
        try {
            Video video = videoService.getVideoById(videoId);
            ApiResponse<Video> response = ApiResponse.<Video>builder()
                    .code(HttpStatus.OK.value())
                    .message("Video retrieved successfully")
                    .result(video)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Video> response = ApiResponse.<Video>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}