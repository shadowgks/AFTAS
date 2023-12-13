package com.example.appgcm.rest.controllers;

import com.example.appgcm.services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/hunting")
@RequiredArgsConstructor
public class HuntingController {
    private final HuntingService huntingService;


}
