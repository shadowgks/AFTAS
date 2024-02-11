package com.example.appgcm.seeder;

import com.example.appgcm.seeder.dbSeeders.LevelSeeder;
import com.example.appgcm.seeder.dbSeeders.FishSeeder;
import com.example.appgcm.seeder.dbSeeders.UserSeeder;
import com.example.appgcm.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppSeeder {
    private final FishSeeder fishSeeder;
    private final LevelSeeder levelSeeder;
    private final UserSeeder userSeeder;

    @PostConstruct
    public void init() {
        levelSeeder.seed();
        fishSeeder.seed();
        userSeeder.seed();
    }
}
