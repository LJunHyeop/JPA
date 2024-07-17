package com.green.greengram.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
public class MyResponse<T> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;
}

/*
{
    "statusCode": 200,
    "resultMsg": "통신 완료",
    " resultData": {
        "name": "홍길동"
    }
}


 */