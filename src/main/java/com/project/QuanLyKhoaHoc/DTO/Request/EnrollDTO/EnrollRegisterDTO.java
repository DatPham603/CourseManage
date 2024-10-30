package com.project.QuanLyKhoaHoc.DTO.Request.EnrollDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollRegisterDTO {
    private Long userId;
    private Long courseId;
    private LocalDateTime enrolledAt;
}
