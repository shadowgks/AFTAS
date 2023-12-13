package com.example.appgcm.mapper;

import com.example.appgcm.dtos.HuntingDto.Requests.HuntingReqDto;
import com.example.appgcm.dtos.HuntingDto.Response.HuntingResDto;
import com.example.appgcm.models.entity.Hunting;

public class HuntingMapper {
    public static HuntingResDto mapToDto(Hunting hunting){
        return HuntingResDto.builder()
                .number_of_fish(hunting.getNumberOfFish())
                .competition(hunting.getCompetition())
                .member(hunting.getMember())
                .fish(hunting.getFish())
                .build();
    }
}
