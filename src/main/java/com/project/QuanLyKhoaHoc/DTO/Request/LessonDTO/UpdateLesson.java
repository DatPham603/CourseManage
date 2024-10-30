package com.project.QuanLyKhoaHoc.DTO.Request.LessonDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLesson {
    @JsonProperty("lessonTitle")
    private String lessonTitle;
    @JsonProperty("content")
    private String content;
    @JsonProperty("courseId")
    private Long courseId;
    @JsonProperty("videoId")
    private Long videoId;
}
