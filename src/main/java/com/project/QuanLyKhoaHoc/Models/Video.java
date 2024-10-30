package com.project.QuanLyKhoaHoc.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "video_id")
    private Long videoId;
    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id", nullable = false)
    private int lessonId;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "duration")
    private int duration;
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
