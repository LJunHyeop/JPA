package com.green.greengram.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"val","main_code_id"}
                )
        }
)
public class SubCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCodeId;
    @ManyToOne@JoinColumn(name = "main_code_id",nullable = false)
    private MainCode mainCodeId;

    @Column(length = 30, nullable = false)
    private String val;
    @Column(length = 30)
    private String description;

}
