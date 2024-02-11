package com.example.appgcm.utils;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Response<T>{
    private String message;
    private T result;
    private Map<String, String> errorsValidation;
    private String error;
}
