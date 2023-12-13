package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.FishDto.Requests.FishReqDto;
import com.example.appgcm.dtos.FishDto.Response.FishResDto;
import com.example.appgcm.mapper.FishMapper;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.services.FishService;
import com.example.appgcm.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/fish")
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    @GetMapping
    public ResponseEntity<Response<List<FishResDto>>> getAllFish(){
        Response<List<FishResDto>> response = new Response<>();
        List<Fish> fish = fishService.getAllFish();
        response.setResult(fish.stream()
                .map(FishMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<FishResDto>> getFishByName(@Valid @PathVariable("name") String name){
        Response<FishResDto> response = new Response<>();
        Fish fish = fishService.getFishByName(name);
        response.setResult(FishMapper.mapToDto(fish));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<FishResDto>> saveFish(@Valid @RequestBody FishReqDto fishDto){
        Response<FishResDto> response = new Response<>();
        Fish fish = fishService.saveFish(fishDto);
        response.setResult(FishMapper.mapToDto(fish));
        response.setMessage("Created Fish Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
