package com.example.appgcm.services;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.models.entity.AppUser;

import java.util.List;

public interface UserService {
    List<AppUser> findAllMembers();
    List<AppUser> searchMembers(String searchTerm);
    AppUser findMemberById(Long id);
    AppUser saveMember(MemberDto member);

    //Auth
    AppUser register(AppUser user);
    AppUser authenticate(AppUser user);
}
