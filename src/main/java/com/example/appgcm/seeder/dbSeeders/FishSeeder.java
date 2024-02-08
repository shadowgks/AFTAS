package com.example.appgcm.seeder.dbSeeders;


import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.models.entity.Level;
import com.example.appgcm.repositories.FishRepository;
import com.example.appgcm.repositories.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FishSeeder {
    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;

    private final Fish[] fishes = {
        Fish.builder().name("Tuna").averageWeight(5.0).level(Level.builder().code(9).build()).build(),
        Fish.builder().name("Salmon").averageWeight(2.0).level(Level.builder().code(2).build()).build(),
        Fish.builder().name("Flounder").averageWeight(0.7).level(Level.builder().code(1).build()).build(),
        Fish.builder().name("Perch").averageWeight(0.8).level(Level.builder().code(1).build()).build(),
        Fish.builder().name("Carp").averageWeight(3.0).level(Level.builder().code(3).build()).build(),
        Fish.builder().name("Cod").averageWeight(2.0).level(Level.builder().code(2).build()).build(),
        Fish.builder().name("Haddock").averageWeight(1.5).level(Level.builder().code(2).build()).build(),
        Fish.builder().name("Red Snapper").averageWeight(2.0).level(Level.builder().code(2).build()).build(),
        Fish.builder().name("Snapper").averageWeight(2.5).level(Level.builder().code(2).build()).build(),
        Fish.builder().name("Bass").averageWeight(3.0).level(Level.builder().code(3).build()).build(),
        Fish.builder().name("Trout").averageWeight(1.0).level(Level.builder().code(1).build()).build(),
        Fish.builder().name("Grouper").averageWeight(4.0).level(Level.builder().code(5).build()).build(),
        Fish.builder().name("Mahi-Mahi").averageWeight(7.0).level(Level.builder().code(6).build()).build()
    };

    private void log(){
        System.out.println("----------------------Fish Seeder----------------------");
    }

    public void seed() {
        this.log();
        if(fishRepository.findAll().isEmpty())
            Arrays.stream(fishes).forEach(fish -> {
                Optional<Level> optionalLevel = levelRepository.findLevelByCode(fish.getLevel().getCode());
                if(optionalLevel.isPresent()){
                    fish.setLevel(optionalLevel.get());
                    fishRepository.save(fish);
                }
            });
    }
}
