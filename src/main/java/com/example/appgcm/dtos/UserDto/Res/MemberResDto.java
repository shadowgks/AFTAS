package com.example.appgcm.dtos.UserDto.Res;

import com.example.appgcm.models.enums.IdentityDocumentType;
import com.example.appgcm.models.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberResDto(
        String fullName,
        String userName,
        String email,
        String password,
        String nationality,
        IdentityDocumentType identityDocumentType,
        String identityNumber,
        RoleType roleType
) {
}
