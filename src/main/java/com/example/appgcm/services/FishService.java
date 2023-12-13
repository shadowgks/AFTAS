package com.example.appgcm.services;

import com.example.appgcm.dtos.FishDto.Requests.FishReqDto;
import com.example.appgcm.models.entity.Fish;

import java.util.List;

public interface FishService {
    List<Fish> getAllFish();
    Fish getFishByName(String name);
    Fish saveFish(FishReqDto fishDto);
}
