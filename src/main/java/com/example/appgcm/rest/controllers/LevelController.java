package com.example.appgcm.rest.controllers;

import com.example.appgcm.mapper.LevelMapper;
import com.example.appgcm.models.entity.Level;
import com.example.appgcm.services.LevelService;
import com.example.appgcm.utils._Response;
import com.example.appgcm.dtos.LevelDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/level")
@PreAuthorize("hasAuthority('s')")
@RequiredArgsConstructor
public class LevelController {
    private final LevelService levelService;

    @GetMapping
    public ResponseEntity<_Response<List<LevelDto>>> getAllLevel(){
        _Response<List<LevelDto>> response = new _Response<>();
        List<Level> level = levelService.getAllLevel();
        response.setResult(level.stream()
                .map(LevelMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<_Response<LevelDto>> saveLevel(@Valid @RequestBody LevelDto levelDto){
        _Response<LevelDto> response = new _Response<>();
        Level level = levelService.saveLevel(levelDto);
        response.setResult(LevelMapper.mapToDto(level));
        response.setMessage("Created Level Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
