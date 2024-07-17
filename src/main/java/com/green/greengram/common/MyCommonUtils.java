package com.green.greengram.common;

import com.green.greengram.user.model.MyUser1Info;
import com.green.greengram.user.model.MyUser1InfoRoles;

import java.util.ArrayList;
import java.util.List;

public class MyCommonUtils {


    public static MyUser1InfoRoles convertToUserInfoRoles(List<MyUser1Info> list){
        if (list == null || list.size() == 0){
            return null;
        }

        MyUser1InfoRoles userInfoRoles = new MyUser1InfoRoles();
        List<String> roles = new ArrayList<>();
        MyUser1Info userInfo = list.get(0);
        userInfoRoles.setUserId(userInfoRoles.getUserId());
        userInfoRoles.setCreatedAt(userInfo.getCreatedAt());
        userInfoRoles.setUpdatedAt(userInfo.getUpdatedAt());
        userInfoRoles.setUid(userInfo.getUid());
        userInfoRoles.setUpw(userInfo.getUpw());
        userInfoRoles.setNm(userInfo.getNm());
        userInfoRoles.setPic(userInfo.getPic());
        userInfoRoles.setRoles(roles);

        for(MyUser1Info ui : list){
            roles.add(ui.getRole());
        }
        return userInfoRoles;
    }
}
