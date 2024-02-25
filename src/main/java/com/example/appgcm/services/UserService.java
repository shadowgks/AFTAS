package com.example.appgcm.services;

import com.example.appgcm.dtos.UserDto.Req.MemberReqDto;
import com.example.appgcm.dtos.UserDto.Req.RoleReqDTO;
import com.example.appgcm.models.entity.AppUser;

import java.util.List;

public interface UserService {
    List<AppUser> findAllMembers();
    List<AppUser> searchMembers(String searchTerm);
    AppUser findMemberById(Long id);
    AppUser saveMember(MemberReqDto member);
    AppUser updateUser(String identityNumber, AppUser reqDTO);

    //Get user in context holder
    Object authContextHolder();

    //Auth
    AppUser register(AppUser user);
    AppUser authenticate(AppUser user);


}
