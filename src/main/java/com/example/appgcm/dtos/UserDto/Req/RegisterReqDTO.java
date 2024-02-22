package com.example.appgcm.dtos.UserDto.Req;


import com.example.appgcm.models.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RegisterReqDTO(
        @NotNull(message = "Full Name must have not be null")
        String fullName,
        @NotNull(message = "Username must have not be null")
        @NotBlank(message = "Username must have not be blank")
        String userName,
        @NotNull(message = "Email must have not be null")
        String email,
        @NotNull(message = "Password must have not be null")
        String password,
        @NotNull(message = "Nationality must have not be null")
        String nationality,
        @NotNull(message = "Identity Document Type must have not be null")
        IdentityDocumentType identityDocumentType,
        @NotNull(message = "Identity Number must have not be null")
        String identityNumber
) {
}
