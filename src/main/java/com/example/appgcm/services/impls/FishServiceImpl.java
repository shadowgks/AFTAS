package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.FishDto;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.services.FishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {
    @Override
    public List<Fish> getAllFish() {
        return null;
    }

    @Override
    public Fish getFishByName(String name) {
        return null;
    }

    @Override
    public Fish saveFish(FishDto fishDto) {
        return null;
    }
}
