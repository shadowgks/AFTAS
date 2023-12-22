package com.example.appgcm.dtos.HuntingDto.Requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record HuntingReqDto(
        @NotNull(message = "Member identity cannot be null")
        String memberIdentity,
        @NotNull(message = "Competition code cannot be null")
        String competitionCode,
        @NotNull(message = "Fish details cannot be null")
        String fishName,
        @NotNull(message = "Weight is required")
        @DecimalMin(value = "0.10", message = "Weight should be greater than 0.10")
        Double weight
) {
}
