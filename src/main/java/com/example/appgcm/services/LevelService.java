package com.example.appgcm.services;

import com.example.appgcm.dtos.FishDto;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Level;

import java.util.List;

public interface LevelService {
    List<Level> getAllLevel();
    Level getLevelById(Long id);
    Level saveLevel(FishDto fishDto);
}
