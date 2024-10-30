package com.example.QuanLyKhoaHoc.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "lessons")
public class Lesson extends BaseEntiy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "lesson_id")
    private Long lessonId;
    @JoinColumn(name = "course_id ")
    @ManyToOne
    private Course course;
    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Video video;
    @Column(name = "lesson_title")
    private String lessonTitle;
    @Column(name = "content")
    private String content;
}
