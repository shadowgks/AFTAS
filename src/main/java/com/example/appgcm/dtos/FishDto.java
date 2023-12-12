package com.example.appgcm.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FishDto(
        Long id,
        @NotNull(message = "Fish name cannot be null")
        @NotBlank(message = "Fish name cannot be blank")
        String name,
        @NotNull(message = "Fish average weight cannot be null")
        @Min(value = 0, message = "Fish average weight cannot be negative")
        @DecimalMin(value = "0.1", message = "Fish average weight should be greater than zero")
        Double averageWeight,
        @NotNull(message = "Fish level cannot be null")
        @Min(value = 1, message = "Fish level should be a positive integer")
        Integer level
) {
}
