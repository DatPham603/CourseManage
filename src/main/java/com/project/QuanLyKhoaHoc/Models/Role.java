package com.project.QuanLyKhoaHoc.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;
    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}
