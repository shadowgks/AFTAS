package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.LevelDto;
import com.example.appgcm.mapper.LevelMapper;
import com.example.appgcm.models.entity.Level;
import com.example.appgcm.services.LevelService;
import com.example.appgcm.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/level")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @GetMapping
    public ResponseEntity<Response<List<LevelDto>>> getAllLevel(){
        Response<List<LevelDto>> response = new Response<>();
        List<Level> level = levelService.getAllLevel();
        response.setResult(level.stream()
                .map(LevelMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Response<LevelDto>> saveLevel(@Valid @RequestBody LevelDto levelDto){
        Response<LevelDto> response = new Response<>();
        Level level = levelService.saveLevel(levelDto);
        response.setResult(LevelMapper.mapToDto(level));
        response.setMessage("Created Level Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
