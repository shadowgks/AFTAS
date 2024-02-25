package com.example.appgcm.dtos.UserDto.Req;

import jakarta.validation.constraints.NotNull;

public record TokenReqDTO(
        @NotNull(message = "Refresh Token must not be null")
        String refreshToken
) {
}
