package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.FishDto.Requests.FishReqDto;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Level;
import com.example.appgcm.repositories.FishRepository;
import com.example.appgcm.repositories.LevelRepository;
import com.example.appgcm.services.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;
    @Override
    public List<Fish> getAllFish() {
        List<Fish> fishList = fishRepository.findAll();
        if(fishList.isEmpty()){
            throw new IllegalArgumentException("Not Found Any Fish");
        }
        return fishList;
    }

    @Override
    public Fish getFishByName(String name) {
        Optional<Fish> fish = Optional.ofNullable(fishRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("This is fish not found")));
        return fish.get();
    }

    @Override
    public Fish saveFish(FishReqDto fishDto) {
        // Find fish by name
        Optional<Fish> fish = fishRepository.findByName(fishDto.name());
        // Check if exists
        if(fish.isPresent()){
            throw new IllegalArgumentException("This fish are already exists");
        }
        // Find level
        Optional<Level> level = Optional.ofNullable(levelRepository.findLevelByCode(fishDto.level())
                .orElseThrow(() -> new IllegalArgumentException("Sorry this level not exists")));
        // Build fish
        Fish fish1 = Fish.builder()
                .name(fishDto.name())
                .level(level.get())
                .averageWeight(fishDto.averageWeight())
                .build();
        return fishRepository.save(fish1);
    }
}
