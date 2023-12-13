package com.example.appgcm.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record LevelDto(
        @NotNull(message = "Code is required")
        Integer code,
        @NotNull(message = "Description is required")
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotNull(message = "Points is required")
        @Min(value = 50, message = "Points must be at least 50")
        Integer points
) {
}
