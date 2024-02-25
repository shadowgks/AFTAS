package com.example.appgcm.dtos.UserDto.Res;

import lombok.Builder;

@Builder
public record TokenResDTO(
        String accessToken,
        String refreshToken
){}
