package com.example.QuanLyKhoaHoc.Service.UserService;

import com.example.QuanLyKhoaHoc.DTO.Request.UserDTO.UserLoginDTO;
import com.example.QuanLyKhoaHoc.DTO.Request.UserDTO.UserRegisterDTO;
import com.example.QuanLyKhoaHoc.Exceptions.PermissionDeniedException;
import com.example.QuanLyKhoaHoc.Models.User;

public interface IUserService {
    User register(UserRegisterDTO userDTO) throws Exception;
    String login(UserLoginDTO userLoginDTO);
}
