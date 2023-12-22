package com.example.appgcm.mapper;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.models.entity.Member;

public class MemberMapper {
    public static MemberDto mapToDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .accessionDate(member.getAccessionDate())
                .nationality(member.getNationality())
                .identityDocumentType(member.getIdentityDocumentType())
                .identityNumber(member.getIdentityNumber())
                .build();
    }
}
