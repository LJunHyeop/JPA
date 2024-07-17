package com.green.greengram.admin;

import com.green.greengram.admin.model.GetApiAdminProvider;
import com.green.greengram.common.model.MyResponse;
import com.green.greengram.security.jwt.JwtTokenProviderV2;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminControllerImpl {

    private final AdminServiceImpl service;
    private final JwtTokenProviderV2 tokenProvider;
    @GetMapping(name = "/provider-count/")
    public MyResponse<List<GetApiAdminProvider>> GetApiAdminProvider(HttpServletRequest req) {
      String token = tokenProvider.resolveToken(req);
      if (token == null) {
        throw new RuntimeException("권한 없음");
      }
        List<GetApiAdminProvider> res = service.GetApiAdmin(token);
     return MyResponse.<List<GetApiAdminProvider>>builder()
             .resultData(res)
             .build();
    }
}
