package com.green.greengram.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MyUser1InfoRoles extends MyUser1 {
    private List<String> roles;

}
