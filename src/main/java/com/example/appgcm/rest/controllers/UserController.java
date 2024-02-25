package com.example.appgcm.rest.controllers;

import com.example.appgcm.config.service.JwtService;
import com.example.appgcm.dtos.UserDto.Req.LoginReqDTO;
import com.example.appgcm.dtos.UserDto.Req.MemberReqDto;
import com.example.appgcm.dtos.UserDto.Req.RegisterReqDTO;
import com.example.appgcm.dtos.UserDto.Req.TokenReqDTO;
import com.example.appgcm.dtos.UserDto.Res.MemberResDto;
import com.example.appgcm.dtos.UserDto.Res.TokenResDTO;
import com.example.appgcm.mapper.MemberMapper;
import com.example.appgcm.mapper.TokenMapper;
import com.example.appgcm.models.entity.RefreshToken;
import com.example.appgcm.services.RefreshTokenService;
import com.example.appgcm.utils._Response;
import jakarta.validation.Valid;
import com.example.appgcm.mapper.UserMapper;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
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
    public ResponseEntity<TokenResDTO> register(@Valid @RequestBody RegisterReqDTO registerReqDTO){
        AppUser user = userService.register(UserMapper.toEntity(registerReqDTO));
        //generate new Access Token
        String accessToken = jwtService.generateAccessToken(user);
        //generate new Refresh Token
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return new ResponseEntity<>(UserMapper.toDto(accessToken, refreshToken), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResDTO> authenticate(@Valid @RequestBody LoginReqDTO loginReqDTO){
        AppUser user = userService.authenticate(UserMapper.toEntity(loginReqDTO));
        //generate new Token
        String accessToken = jwtService.generateAccessToken(user);
        //generate new Refresh Token
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return new ResponseEntity<>(UserMapper.toDto(accessToken, refreshToken), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResDTO> refreshToken(@RequestBody TokenReqDTO request) {
        RefreshToken refreshToken = refreshTokenService.generateNewToken(TokenMapper.toEntity(request));
        return ResponseEntity.ok(TokenMapper.toDto(refreshToken));
    }

}
