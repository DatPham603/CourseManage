package com.example.QuanLyKhoaHoc.Controller;

import com.example.QuanLyKhoaHoc.DTO.Request.UserDTO.UserLoginDTO;
import com.example.QuanLyKhoaHoc.DTO.Request.UserDTO.UserRegisterDTO;
import com.example.QuanLyKhoaHoc.DTO.Response.ApiResponse;
import com.example.QuanLyKhoaHoc.Models.User;
import com.example.QuanLyKhoaHoc.Service.UserService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            User user = userService.register(userRegisterDTO);
            ApiResponse<User> response = ApiResponse.<User>builder()
                    .code(HttpStatus.CREATED.value())
                    .message("User registered successfully")
                    .result(user)
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<User> response = ApiResponse.<User>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(userLoginDTO);
            ApiResponse<String> response = ApiResponse.<String>builder()
                    .code(HttpStatus.OK.value())
                    .message("Login successful")
                    .result(token)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<String> response = ApiResponse.<String>builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}