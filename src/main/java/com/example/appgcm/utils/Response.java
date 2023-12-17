package com.example.appgcm.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class Response<T>{
    private String message;
    private T result;
    private Map<String, String> errorsValidation;
    private String error;
}
