package com.example.appgcm.mapper;

import com.example.appgcm.dtos.UserDto.Req.LoginReqDTO;
import com.example.appgcm.dtos.UserDto.Req.RegisterReqDTO;
import com.example.appgcm.dtos.UserDto.Res.UserResDTO;
import com.example.appgcm.models.entity.AppUser;

public class UserMapper {
    public static AppUser toEntity(RegisterReqDTO registerReqDto){
        return AppUser.builder()
                .email(registerReqDto.email())
                .password(registerReqDto.password())
                .userName(registerReqDto.userName())
                .fullName(registerReqDto.fullName())
                .build();
    }

    public static AppUser toEntity(LoginReqDTO loginDTO){
        return AppUser.builder()
                .email(loginDTO.email())
                .password(loginDTO.password())
                .build();
    }

    public static UserResDTO toDto(AppUser user, String token){
        return UserResDTO.builder()
                .accessToken(token)
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
