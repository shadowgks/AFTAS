package com.example.appgcm.dtos.UserDto.Res;

import com.example.appgcm.models.entity.Role;
import com.example.appgcm.models.enums.IdentityDocumentType;
import com.example.appgcm.models.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record MemberResDto(
        String fullName,
        String userName,
        String email,
        String nationality,
        LocalDate accessionDate,
        IdentityDocumentType identityDocumentType,
        String identityNumber,
        Boolean isWorking,
        Set<Role> role
) {
}
