package com.example.appgcm.dtos.UserDto.Res;

import com.example.appgcm.models.entity.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResDTO(
        String accessToken,
        String refreshToken
){}
