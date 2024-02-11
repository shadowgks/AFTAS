package com.example.appgcm.seeder.dbSeeders;

import com.example.appgcm.models.enums.ActionType;
import com.example.appgcm.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {
    private final PermissionRepository permissionRepository;
    public void seed(){
        if(permissionRepository.findAll().isEmpty()){
            for (ActionType actionType : ActionType.values()) {

            }
        }
    }
}
