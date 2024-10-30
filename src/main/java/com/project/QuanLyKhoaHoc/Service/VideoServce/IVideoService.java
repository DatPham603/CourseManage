package com.project.QuanLyKhoaHoc.Service.VideoServce;

import com.project.QuanLyKhoaHoc.DTO.Request.VideoDTO.VideoDTO;
import com.project.QuanLyKhoaHoc.Exceptions.DataNotFoundException;
import com.project.QuanLyKhoaHoc.Models.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IVideoService {
     Video uploadVideo(MultipartFile file, VideoDTO videoDTO) throws IOException;
     public Video getVideoById(Long videoId) throws DataNotFoundException;
}
