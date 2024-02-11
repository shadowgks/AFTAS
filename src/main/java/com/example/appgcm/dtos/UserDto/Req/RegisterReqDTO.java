package com.example.appgcm.dtos.UserDto.Req;


import jakarta.validation.constraints.NotNull;


public record RegisterReqDTO(
        @NotNull(message = "Full Name must have not be null")
        String fullName,
        @NotNull(message = "Username must have not be null")
        String userName,
        @NotNull(message = "Email must have not be null")
        String email,
        @NotNull(message = "Password must have not be null")
        String password
) {
}
