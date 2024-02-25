package com.example.appgcm.mapper;

import com.example.appgcm.dtos.UserDto.Req.MemberReqDto;
import com.example.appgcm.dtos.UserDto.Req.RoleReqDTO;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.models.entity.Role;

import java.util.Set;

public class RoleMapper {
    public static AppUser toEntity(RoleReqDTO reqDto){
        return AppUser.builder()
                .isWorking(reqDto.isWorking())
                .roles(Set.of(Role.builder().name(reqDto.roleType()).build()))
                .build();
    }
}
