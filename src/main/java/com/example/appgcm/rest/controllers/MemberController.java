package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.mapper.MemberMapper;
import com.example.appgcm.utils.Response;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('MEMBER:ALL')")
public class MemberController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Response<MemberDto>> createMember(@Valid @RequestBody MemberDto reqDto){
        Response<MemberDto> response = new Response<>();
        AppUser user = userService.saveMember(reqDto);
        response.setResult(MemberMapper.mapToDto(user));
        response.setMessage("Created Member Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MemberDto>> findMemberById(@Valid @PathVariable Long id){
        Response<MemberDto> response = new Response<>();
        AppUser user = userService.findMemberById(id);
        response.setResult(MemberMapper.mapToDto(user));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<MemberDto>>> findAllMembers(){
        Response<List<MemberDto>> response = new Response<>();
        List<AppUser> userList = userService.findAllMembers();
        response.setResult(userList
                .stream()
                .map(MemberMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<Response<List<MemberDto>>> findAllMembersBySearch(@PathVariable("searchTerm") String searchTerm){
        Response<List<MemberDto>> listResponse = new Response<>();
        List<AppUser> userList = userService.searchMembers(searchTerm);
        listResponse.setResult(userList
                .stream()
                .map(MemberMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(listResponse);
    }
}
