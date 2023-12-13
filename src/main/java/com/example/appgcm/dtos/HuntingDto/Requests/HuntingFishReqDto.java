package com.example.appgcm.dtos.HuntingDto.Requests;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record HuntingFishReqDto(
        @NotBlank(message = "Please provide a name")
        String name,
        @NotNull(message = "Weight is required")
        @DecimalMin(value = "0.10", message = "Weight should be greater than 0.10")
        Double weight
) {
}
