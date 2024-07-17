package com.green.greengram.user;

import com.green.greengram.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
     List<MyUser1> selTest(long userId);

     int signUpPostReq(SignUpPostReq p);
     List<MyUser1Info> signInPost(SignInPostReq p);
     UserInfoGetRes selProfileUserInfo(UserInfoGetReq p);
     int updProfilePic(UserProfilePatchReq p);
}
