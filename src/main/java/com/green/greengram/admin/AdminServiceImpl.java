package com.green.greengram.admin;

import com.green.greengram.admin.model.GetApiAdminProvider;
import com.green.greengram.admin.model.GetApiAdminRes;
import com.green.greengram.security.MyUserDetails;
import com.green.greengram.security.jwt.JwtTokenProviderV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl {

        private AdminMapper mapper;
        private  final JwtTokenProviderV2 tokenProvider;
    public List<GetApiAdminProvider> GetApiAdmin(String token){
        Authentication auth = tokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        long userId = userDetails.getMyUser().getUserId();
        GetApiAdminProvider provider = new GetApiAdminProvider();
        provider.setUserId(userId);
        return mapper.GetApiAdmin(provider);
    }
}
