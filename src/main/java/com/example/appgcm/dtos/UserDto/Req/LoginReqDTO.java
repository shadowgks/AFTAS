package com.example.appgcm.dtos.UserDto.Req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginReqDTO(
        @NotNull(message = "Email must have not be null")
        @Email(message = "Email format is not valid")
        String email,
        @NotNull(message = "Password must have not be null")
        String password
) {
}
