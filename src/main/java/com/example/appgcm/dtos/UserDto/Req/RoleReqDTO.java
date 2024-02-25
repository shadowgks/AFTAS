package com.example.appgcm.dtos.UserDto.Req;

import com.example.appgcm.models.enums.RoleType;
import jakarta.validation.constraints.NotNull;

public record RoleReqDTO(
        RoleType roleType,
        Boolean isWorking
) {
}
