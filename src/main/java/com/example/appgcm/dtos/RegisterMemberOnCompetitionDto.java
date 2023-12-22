package com.example.appgcm.dtos;

import jakarta.validation.constraints.NotNull;

public record RegisterMemberOnCompetitionDto(
        @NotNull(message = "Member cannot be null")
        String memberIdentity,
        @NotNull(message = "Competition cannot be null")
        String competitionCode
) {
}
