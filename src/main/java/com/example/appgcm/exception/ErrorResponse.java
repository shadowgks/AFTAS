package com.example.appgcm.exception;

public record ErrorResponse(
        String timeStamp,
        Integer status,
        String error,
        String result,
        String path
){
}
