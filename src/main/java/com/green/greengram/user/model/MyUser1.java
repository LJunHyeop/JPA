package com.green.greengram.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MyUser1 {
    private long userId;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String createdAt;
    private String updatedAt;
}
