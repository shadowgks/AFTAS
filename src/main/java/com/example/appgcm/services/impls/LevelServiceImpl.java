package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.FishDto;
import com.example.appgcm.models.entity.Level;
import com.example.appgcm.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    @Override
    public List<Level> getAllLevel() {
        return null;
    }

    @Override
    public Level getLevelById(Long id) {
        return null;
    }

    @Override
    public Level saveLevel(FishDto fishDto) {
        return null;
    }
}
