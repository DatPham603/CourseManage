package com.example.QuanLyKhoaHoc.Repository;

import com.example.QuanLyKhoaHoc.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends JpaRepository<Video,Long> {
}
