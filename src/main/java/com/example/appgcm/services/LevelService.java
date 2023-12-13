package com.example.appgcm.services;

import com.example.appgcm.dtos.LevelDto;
import com.example.appgcm.models.entity.Level;

import java.util.List;

public interface LevelService {
    List<Level> getAllLevel();
    Level getLevelByCode(Integer code);
    Level saveLevel(LevelDto levelDto);
}
