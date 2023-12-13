package com.example.appgcm.dtos;

import jakarta.validation.constraints.NotNull;

public record RegisterMemberOnCompetitionDto(
        @NotNull(message = "Member cannot be null")
        Long member_id,
        @NotNull(message = "Competition cannot be null")
        Long competition_id
) {
}
