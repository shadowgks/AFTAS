package com.example.appgcm.rest.controllers;

import com.example.appgcm.mapper.FishMapper;
import com.example.appgcm.models.entity.Fish;
import com.example.appgcm.services.FishService;
import com.example.appgcm.utils._Response;
import com.example.appgcm.dtos.FishDto.Requests.FishReqDto;
import com.example.appgcm.dtos.FishDto.Response.FishResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/fish")
@PreAuthorize("hasAnyAuthority('ROLE:MANAGER', 'ROLE:JURY')")
@RequiredArgsConstructor
public class FishController {
    private final FishService fishService;

    @GetMapping
    public ResponseEntity<_Response<List<FishResDto>>> getAllFish(){
        _Response<List<FishResDto>> response = new _Response<>();
        List<Fish> fish = fishService.getAllFish();
        response.setResult(fish.stream()
                .map(FishMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<_Response<FishResDto>> getFishByName(@Valid @PathVariable("name") String name){
        _Response<FishResDto> response = new _Response<>();
        Fish fish = fishService.getFishByName(name);
        response.setResult(FishMapper.mapToDto(fish));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<_Response<FishResDto>> saveFish(@Valid @RequestBody FishReqDto fishDto){
        _Response<FishResDto> response = new _Response<>();
        Fish fish = fishService.saveFish(fishDto);
        response.setResult(FishMapper.mapToDto(fish));
        response.setMessage("Created Fish Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
