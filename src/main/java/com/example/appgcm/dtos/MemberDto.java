package com.example.appgcm.dtos;

import com.example.appgcm.models.enums.IdentityDocumentType;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberDto(
        Long id,
        @NotNull(message = "Full name is required")
        @NotBlank(message = "Full name cannot be blank")
        String fullName,
        LocalDate accessionDate,
        @NotNull(message = "Nationality is required")
        @NotBlank(message = "Nationality cannot be blank")
        String nationality,
        @NotNull(message = "Please select an identity type")
        IdentityDocumentType identityDocumentType,
        @NotNull(message = "Identity number is required")
        @NotBlank(message = "Identity number cannot be blank")
        String identityNumber
) {
}
