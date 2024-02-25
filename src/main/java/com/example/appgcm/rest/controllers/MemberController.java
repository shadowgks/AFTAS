package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.UserDto.Req.MemberReqDto;
import com.example.appgcm.dtos.UserDto.Req.RoleReqDTO;
import com.example.appgcm.dtos.UserDto.Res.MemberResDto;
import com.example.appgcm.mapper.MemberMapper;
import com.example.appgcm.mapper.RoleMapper;
import com.example.appgcm.utils._Response;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE:MANAGER')")
public class MemberController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<_Response<MemberResDto>> createMember(@Valid @RequestBody MemberReqDto reqDto){
        _Response<MemberResDto> response = new _Response<>();
        AppUser user = userService.saveMember(reqDto);
        response.setResult(MemberMapper.mapToDto(user));
        response.setMessage("Created Member Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<_Response<MemberResDto>> findMemberById(@Valid @PathVariable Long id){
        _Response<MemberResDto> response = new _Response<>();
        AppUser user = userService.findMemberById(id);
        response.setResult(MemberMapper.mapToDto(user));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<_Response<List<MemberResDto>>> findAllMembers(){
        _Response<List<MemberResDto>> response = new _Response<>();
        List<AppUser> userList = userService.findAllMembers();
        response.setResult(userList
                .stream()
                .map(MemberMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<_Response<List<MemberResDto>>> findAllMembersBySearch(@PathVariable("searchTerm") String searchTerm){
        _Response<List<MemberResDto>> listResponse = new _Response<>();
        List<AppUser> userList = userService.searchMembers(searchTerm);
        listResponse.setResult(userList
                .stream()
                .map(MemberMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(listResponse);
    }

    @PatchMapping("/update-user/{identityOrEnabled}")
    public ResponseEntity<_Response<?>> updateUser(@Valid @RequestBody RoleReqDTO reqDto,
                                                   @PathVariable("identityOrEnabled") String identityOrEnabled){
        _Response<MemberResDto> response = new _Response<>();
        userService.updateUser(identityOrEnabled, RoleMapper.toEntity(reqDto));
        response.setMessage("Updated Successfully");
        return ResponseEntity.ok(response);
    }

}
