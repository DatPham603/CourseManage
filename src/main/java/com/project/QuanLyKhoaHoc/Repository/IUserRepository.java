package com.project.QuanLyKhoaHoc.Repository;

import com.project.QuanLyKhoaHoc.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    boolean existsByUserName(String userName);
    Optional<User> findByName(String userName);
}
