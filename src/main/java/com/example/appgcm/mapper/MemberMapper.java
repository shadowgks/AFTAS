package com.example.appgcm.mapper;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.models.entity.AppUser;

public class MemberMapper {
    public static MemberDto mapToDto(AppUser user){
        return MemberDto.builder()
                .id(user.getId())
                .firstName(user.getFullName())
                .accessionDate(user.getAccessionDate())
                .nationality(user.getNationality())
                .identityDocumentType(user.getIdentityDocumentType())
                .identityNumber(user.getIdentityNumber())
                .build();
    }
}
