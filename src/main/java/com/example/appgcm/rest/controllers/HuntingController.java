package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.HuntingDto.Requests.HuntingReqDto;
import com.example.appgcm.dtos.HuntingDto.Response.HuntingResDto;
import com.example.appgcm.mapper.HuntingMapper;
import com.example.appgcm.models.entity.Hunting;
import com.example.appgcm.services.HuntingService;
import com.example.appgcm.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hunting")
@RequiredArgsConstructor
public class HuntingController {
    private final HuntingService huntingService;

    @PostMapping("/hunt")
    public ResponseEntity<Response<HuntingResDto>> createHunting(@Valid @RequestBody HuntingReqDto reqDto){
        Response<HuntingResDto> response = new Response<>();
        Hunting hunting = huntingService.huntingFish(reqDto);
        response.setMessage("Hunting successfully");
        response.setResult(HuntingMapper.mapToDto(hunting));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
