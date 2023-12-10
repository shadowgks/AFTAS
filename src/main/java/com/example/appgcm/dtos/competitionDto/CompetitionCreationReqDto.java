package com.example.appgcm.dtos.competitionDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record CompetitionCreationReqDto(
        @NotNull(message = "Name cannot be null")
        @Future(message = "Date must be in the future")
        LocalDate date,
        @NotNull(message = "Start time cannot be null")
        LocalTime startTime,
        @NotNull(message = "End time cannot be null")
        LocalTime endTime,
        @NotNull(message = "Number of participants cannot be null")
        @Positive(message = "Number of participants must be positive")
        Integer numberOfParticipants,
        @NotNull(message = "Location cannot be null")
        @NotBlank(message = "Location cannot be blank")
        String location,
        @NotNull(message = "Amount cannot be null")
        @PositiveOrZero(message = "Amount must be positive")
        Double amount
) {
}
