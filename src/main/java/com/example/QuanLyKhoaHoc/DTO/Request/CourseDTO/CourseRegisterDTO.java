package com.example.QuanLyKhoaHoc.DTO.Request.CourseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRegisterDTO {
    @JsonProperty("course_Name")
    private String courseName;
    @JsonProperty("description")
    private String description;
}
