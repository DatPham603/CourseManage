package com.project.QuanLyKhoaHoc.Models;

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
@Table(name = "courses")
public class Course extends BaseEntiy {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "description")
    private String description;
}
