package com.project.QuanLyKhoaHoc.DTO.Request.VideoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDTO {
    private String title;
    private String url;
    private int duration;
    private int lessonId;
}
