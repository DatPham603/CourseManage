package com.example.QuanLyKhoaHoc.Service.VideoServce;

import com.example.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.example.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.example.QuanLyKhoaHoc.Models.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IVideoService {
     Video uploadVideo(MultipartFile file, VideoDTO videoDTO) throws IOException;
     public Video getVideoById(Long videoId) throws DataNotFoundException;
}
