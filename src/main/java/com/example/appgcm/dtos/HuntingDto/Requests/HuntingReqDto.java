package com.example.appgcm.dtos.HuntingDto.Requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record HuntingReqDto(
        @NotNull(message = "Member ID cannot be null")
        Long memberId,
        @NotNull(message = "Competition ID cannot be null")
        Long competitionId,
        @NotNull(message = "Fish details cannot be null")
        HuntingFishReqDto fish
) {
}
