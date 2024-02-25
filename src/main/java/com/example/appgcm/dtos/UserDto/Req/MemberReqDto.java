package com.example.appgcm.dtos.UserDto.Req;

import com.example.appgcm.models.enums.IdentityDocumentType;
import com.example.appgcm.models.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberReqDto(
        @JsonProperty("user")
        @NotNull(message = "User must not be null")
        RegisterReqDTO registerReqDTO,
        @NotNull(message = "Role must not be null")
        RoleType roleType

) {
}
