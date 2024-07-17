package com.green.greengram.entity;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"from_user_id", "to_user_id"}
                )
        }
)
public class UserFollow extends CreatedAt{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFollow;

    @ManyToOne // Many 가 나 One 이 받아오는곳
    @JoinColumn(name = "from_user_id",nullable = false) // 내가 받고싶은 컬럼명
    private User fromUser;
    @ManyToOne @JoinColumn(name = "to_user_id",nullable = false)
    private User toUser;
}
