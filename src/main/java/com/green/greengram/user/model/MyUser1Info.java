package com.green.greengram.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyUser1Info extends MyUser1 {
    private String role;

    public MyUser1Info(long userId, String uid, String upw, String nm, String pic, String createdAt, String updatedAt) {

    }
}
