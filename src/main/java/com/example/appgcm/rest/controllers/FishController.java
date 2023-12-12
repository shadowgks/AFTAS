package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.FishDto;
import com.example.appgcm.mapper.FishMapper;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.services.FishService;
import com.example.appgcm.services.LevelService;
import com.example.appgcm.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/fish")
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    @GetMapping
    public ResponseEntity<Response<List<FishDto>>> getAllFish(){
        Response<List<FishDto>> response = new Response<>();
        List<Fish> fish = fishService.getAllFish();
        response.setResult(fish.stream()
                .map(FishMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }
}
