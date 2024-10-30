package com.project.QuanLyKhoaHoc.Service.UserService;

import com.project.QuanLyKhoaHoc.DTO.Request.UserDTO.UserLoginDTO;
import com.project.QuanLyKhoaHoc.DTO.Request.UserDTO.UserRegisterDTO;
import com.project.QuanLyKhoaHoc.Exceptions.PermissionDeniedException;
import com.project.QuanLyKhoaHoc.Models.Role;
import com.project.QuanLyKhoaHoc.Models.User;
import com.project.QuanLyKhoaHoc.Repository.IRoleRepository;
import com.project.QuanLyKhoaHoc.Repository.IUserRepository;
import com.project.QuanLyKhoaHoc.Utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    @Override
    public User register(UserRegisterDTO userDTO) throws Exception {
        if(userRepository.existsByUserName(userDTO.getUserName())){
            throw new DataIntegrityViolationException("User existed");
        }
        User newUser = User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .build();
        String password = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow();
        if(role.getRoleName().equals(Role.ADMIN)){
            throw new PermissionDeniedException("You cannot create Admin account");
        }
        newUser.setRole(role);
        return userRepository.save(newUser);
    }
    @Override
    public String login(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findByName(userLoginDTO.getUserName());
        if(user.isEmpty()){
            throw new DataIntegrityViolationException("Invalid phonenumber/password");
        }
        if(!passwordEncoder.matches(userLoginDTO.getPassword(),user.get().getPassword())){
            throw new DataIntegrityViolationException("Wrong phone number / password");
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(),userLoginDTO.getPassword(),user.get().getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return jwtUtils.generateToken(user.get());
    }
}
