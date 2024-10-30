package com.example.QuanLyKhoaHoc.DTO.Request.UserDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;
}
