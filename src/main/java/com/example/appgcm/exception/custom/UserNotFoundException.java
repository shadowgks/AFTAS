package com.example.appgcm.exception.custom;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String content){
        super(content);
    }
}
