package com.example.appgcm.services;

import com.example.appgcm.dtos.HuntingDto.Requests.HuntingReqDto;
import com.example.appgcm.models.entity.Hunting;

public interface HuntingService {
    Hunting sumHuntingFish(HuntingReqDto huntingDto);
}
