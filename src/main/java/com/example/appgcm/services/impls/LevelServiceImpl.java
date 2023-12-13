package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.LevelDto;
import com.example.appgcm.models.entity.Level;
import com.example.appgcm.repositories.LevelRepository;
import com.example.appgcm.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    @Override
    public List<Level> getAllLevel() {
        List<Level> levelList = levelRepository.findAll();
        if(levelList.isEmpty()){
            throw new IllegalArgumentException("Not Found Any Level");
        }
        return levelList;
    }

    @Override
    public Level getLevelByCode(Integer code) {
        Optional<Level> level = Optional.ofNullable(levelRepository.findLevelByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("This is level not found")));
        return level.get();
    }
    @Override
    public Level saveLevel(LevelDto levelDto) {
        Optional<Level> level = levelRepository.findLevelByPoints(levelDto.points());
        if(level.isPresent()){
            throw new IllegalArgumentException("This point is already exists");
        }

        return null;
    }
}
