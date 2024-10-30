package com.project.QuanLyKhoaHoc.Service.UserService;

import com.project.QuanLyKhoaHoc.DTO.Request.UserDTO.UserLoginDTO;
import com.project.QuanLyKhoaHoc.DTO.Request.UserDTO.UserRegisterDTO;
import com.project.QuanLyKhoaHoc.Models.User;

public interface IUserService {
    User register(UserRegisterDTO userDTO) throws Exception;
    String login(UserLoginDTO userLoginDTO);
}
