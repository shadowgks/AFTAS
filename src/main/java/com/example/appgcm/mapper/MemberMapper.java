package com.example.appgcm.mapper;

import com.example.appgcm.dtos.UserDto.Req.MemberReqDto;
import com.example.appgcm.dtos.UserDto.Res.MemberResDto;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.models.entity.Role;
import com.example.appgcm.models.enums.RoleType;

import java.util.Set;
import java.util.stream.Collectors;

public class MemberMapper {
    public static MemberResDto mapToDto(AppUser user){
        return MemberResDto.builder()
                .fullName(user.getFullName())
                .userName(user.getUserNamee())
                .email(user.getEmail())
                .nationality(user.getNationality())
                .accessionDate(user.getAccessionDate())
                .identityDocumentType(user.getIdentityDocumentType())
                .identityNumber(user.getIdentityNumber())
                .isWorking(user.getIsWorking())
                .role(user.getRoles())
                .build();
    }

    public static AppUser toEntity(MemberReqDto reqDto){
        return AppUser.builder()
                .email(reqDto.registerReqDTO().email())
                .password(reqDto.registerReqDTO().password())
                .userNamee(reqDto.registerReqDTO().userName())
                .fullName(reqDto.registerReqDTO().fullName())
                .identityNumber(reqDto.registerReqDTO().identityNumber())
                .identityDocumentType(reqDto.registerReqDTO().identityDocumentType())
                .nationality(reqDto.registerReqDTO().nationality())
                .build();
    }
}
