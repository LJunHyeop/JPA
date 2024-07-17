package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass // 애가 부모가 될꺼임 맵핑가능하게
@EntityListeners(AuditingEntityListener.class)
public class CreatedAt {

    @Column(nullable = false)
    @CreatedDate // JPA 가 insert 때 현재 일시 값을 주입
    private LocalDateTime createdAt;
}
