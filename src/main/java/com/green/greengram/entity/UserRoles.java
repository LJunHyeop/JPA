package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "user_roles"
)
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;
    @ManyToOne @JoinColumn(name="user_id",nullable = false)
    private UserRoles userId;
    @ManyToOne @JoinColumn(name="role_cd",nullable = false)
    private UserRoles roleCd;

    @Column(length = 20)
    private String role;
}
