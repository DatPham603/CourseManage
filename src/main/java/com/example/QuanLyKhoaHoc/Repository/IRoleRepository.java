package com.example.QuanLyKhoaHoc.Repository;

import com.example.QuanLyKhoaHoc.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findById(Long roleId);
}
