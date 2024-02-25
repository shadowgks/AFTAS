package com.example.appgcm.exception.custom;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String content){
        super(content);
    }
}
