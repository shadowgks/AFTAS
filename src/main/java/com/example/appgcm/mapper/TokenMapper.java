package com.example.appgcm.mapper;

import com.example.appgcm.dtos.UserDto.Req.TokenReqDTO;
import com.example.appgcm.dtos.UserDto.Res.TokenResDTO;
import com.example.appgcm.models.entity.RefreshToken;

public class TokenMapper {
    public static RefreshToken toEntity(TokenReqDTO tokenReqDTO){
        return RefreshToken.builder()
                .token(tokenReqDTO.refreshToken())
                .build();
    }

    public static TokenResDTO toDto(RefreshToken refreshToken){
        return TokenResDTO.builder()
                .accessToken(refreshToken.getToken())
                .build();
    }
}
