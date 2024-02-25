package com.example.appgcm.dtos.CompetitionDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionReqDto(
        @NotNull(message = "Name cannot be null")
        @Future(message = "Date must be in the future")
        LocalDate date,
        @NotNull(message = "Start time cannot be null")
        LocalTime startTime,
        @NotNull(message = "End time cannot be null")
        LocalTime endTime,
        @NotNull(message = "Number of participants cannot be null")
        @Positive(message = "Number of participants must be positive")
        @Min(value = 1)
        Integer numberOfParticipants,
        @NotNull(message = "Location cannot be null")
        @NotBlank(message = "Location cannot be blank")
//        @Min(value = 3, message = "Name location must be greater than or equal to 3")
        String location,
        @NotNull(message = "Amount cannot be null")
        @PositiveOrZero(message = "Amount must be positive")
        Double amount
) {
}
