package com.example.appgcm.dtos.HuntingDto.Response;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Member;
import lombok.Builder;

@Builder
public record HuntingResDto(
        Integer number_of_fish,
        Competition competition,
        Fish fish,
        Member member
) {
}
