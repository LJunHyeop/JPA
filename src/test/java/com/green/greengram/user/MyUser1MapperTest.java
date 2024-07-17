package com.green.greengram.user;

import com.green.greengram.user.model.MyUser1;
import com.green.greengram.user.model.UserInfoGetReq;
import com.green.greengram.user.model.UserInfoGetRes;
import com.green.greengram.user.model.UserProfilePatchReq;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("tdd")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MyUser1MapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void signInPost() {
        MyUser1 myUser11 = mapper.signInPost("사용자1");
        List<MyUser1> myUser1List1 = mapper.selTest(myUser11.getUserId());
        MyUser1 myUser11Comp = myUser1List1.get(0);
        assertEquals(myUser11Comp, myUser11);

        MyUser1 myUser13 = mapper.signInPost("사용자3");
        List<MyUser1> myUser1List3 = mapper.selTest(myUser13.getUserId());
        MyUser1 myUser13Comp = myUser1List3.get(0);
        assertEquals(myUser13Comp, myUser13);

        MyUser1 myUser1No = mapper.signInPost("sdklsdfnkl");
        assertNull(myUser1No, "없는 사용자 레코드 넘어옴");
    }

    @Test
    void selProfileUserInfo() {
        UserInfoGetReq req1 = new UserInfoGetReq(2);
        req1.setSignedUserId(1);
        UserInfoGetRes res1 = mapper.selProfileUserInfo(req1);

        UserInfoGetRes expectedRes1 = new UserInfoGetRes();
        expectedRes1.setNm("홍길동");
        expectedRes1.setPic("2bb291c1-e57e-4455-98ec-fcc4edb7ae41.jfif");
        expectedRes1.setCreatedAt("2024-05-03 14:35:03");
        expectedRes1.setFeedCnt(4);
        expectedRes1.setFavCnt(2);
        expectedRes1.setFollowing(4);
        expectedRes1.setFollower(2);
        expectedRes1.setFollowState(3);

        assertEquals(expectedRes1, res1, "signedUserId:2, profileUserId:1 내용 상이");

        // 다른 UserInfoGetReq 값으로 체크 2~3 더 추가

        // 없는 UserInfoGetReq 값으로 null이 넘어오는지 체크
    }

    @Test
    void updProfilePicMe() {

    }

    @Test
    void updProfilePicYou() {
        //수정 되기 전 전체 리스트 가져옴
        List<MyUser1> beforeMyUser1List = mapper.selTest(0);

        long modUserId = 1;
        String picName1 = "test.jpg";
        UserProfilePatchReq req1 = new UserProfilePatchReq();
        req1.setSignedUserId(modUserId);
        req1.setPicName(picName1);
        int affectedRows = mapper.updProfilePic(req1);

        assertEquals(1, affectedRows);

        List<MyUser1> myUser1List1 = mapper.selTest(modUserId);
        MyUser1 myUser11 = myUser1List1.get(0);

        assertEquals(picName1, myUser11.getPic());

        //수정 된 후 전체 리스트 가져옴
        List<MyUser1> afterMyUser1List = mapper.selTest(0);

        for(int i = 0; i< beforeMyUser1List.size(); i++) {
            MyUser1 beforeMyUser1 = beforeMyUser1List.get(i);
            if(beforeMyUser1.getUserId() == modUserId) {
                assertNotEquals(beforeMyUser1, afterMyUser1List.get(i));
                continue;
            }
            assertEquals(beforeMyUser1, afterMyUser1List.get(i));
        }


    }

    @Test
    void updProfilePicNoUser() {
        List<MyUser1> beforeMyUser1List = mapper.selTest(0);

        //없는 userId로 update 시도시 affectedRows 0이 넘어오는지 체크
        String picName1 = "test.jpg";
        UserProfilePatchReq req1 = new UserProfilePatchReq();
        req1.setSignedUserId(100);
        req1.setPicName(picName1);
        int affectedRows = mapper.updProfilePic(req1);

        assertEquals(0, affectedRows);

        List<MyUser1> afterMyUser1List = mapper.selTest(0);

        for(int i = 0; i< beforeMyUser1List.size(); i++) {
            assertEquals(beforeMyUser1List.get(i), afterMyUser1List.get(i));
        }
    }
}