package com.example.appgcm.rest.controllers;

import com.example.appgcm.config.service.JwtService;
import com.example.appgcm.dtos.UserDto.Req.LoginReqDTO;
import com.example.appgcm.dtos.UserDto.Req.RegisterReqDTO;
import com.example.appgcm.dtos.UserDto.Res.UserResDTO;
import com.example.appgcm.utils._Response;
import jakarta.validation.Valid;
import com.example.appgcm.mapper.UserMapper;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    //    @GetMapping
//    public ResponseEntity<List<AppUser>> findAllUser(){
//        List<AppUser> userList = userService.();
//        return ResponseEntity.ok(userList);
//    }
//    @GetMapping("/{username}")
//    public ResponseEntity<AppUser> findUserByUsername(@Valid @PathVariable("username") String username){
//        AppUser user = userService.findByUsernameUser(username);
//        return ResponseEntity.ok(user);
//    }
    @PostMapping("/register")
    public ResponseEntity<UserResDTO> register(@Valid @RequestBody RegisterReqDTO registerReqDTO){
        _Response<UserResDTO> response = new _Response();
        AppUser user = userService.register(UserMapper.toEntity(registerReqDTO));
        //generate new Token
        String jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(UserMapper.toDto(user, jwtToken), HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<UserResDTO> authenticate(@Valid @RequestBody LoginReqDTO loginReqDTO){
        _Response<UserResDTO> response = new _Response();
        AppUser user = userService.authenticate(UserMapper.toEntity(loginReqDTO));
        //generate new Token
        String jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(UserMapper.toDto(user, jwtToken), HttpStatus.OK);
    }
}
