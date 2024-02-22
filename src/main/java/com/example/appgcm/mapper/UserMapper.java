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
                .identityNumber(registerReqDto.identityNumber())
                .identityDocumentType(registerReqDto.identityDocumentType())
                .nationality(registerReqDto.nationality())
                .build();
    }

    public static AppUser toEntity(LoginReqDTO loginDTO){
        return AppUser.builder()
                .email(loginDTO.email())
                .password(loginDTO.password())
                .build();
    }

    public static UserResDTO toDto(String accesstoken, String refreshToken){
        return UserResDTO.builder()
                .accessToken(accesstoken)
                .refreshToken(refreshToken)
                .build();
    }
}
