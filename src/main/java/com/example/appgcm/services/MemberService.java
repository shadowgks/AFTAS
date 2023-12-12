package com.example.appgcm.services;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.models.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> findAllMembers();
    Member findMemberById(Long id);
    Member saveMember(MemberDto member);
}
