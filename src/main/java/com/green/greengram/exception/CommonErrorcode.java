package com.green.greengram.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum CommonErrorcode implements ErrorCode{

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생 관리자에게 문의해 주세용"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST,"잘못된 파라미터 입니다.");

    private final HttpStatus httpStatus;
    private final String message;


}

