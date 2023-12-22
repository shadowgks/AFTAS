package com.example.appgcm.rest.controllers;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.mapper.MemberMapper;
import com.example.appgcm.models.entity.Member;
import com.example.appgcm.services.CompetitionService;
import com.example.appgcm.services.MemberService;
import com.example.appgcm.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Response<MemberDto>> createMember(@Valid @RequestBody MemberDto reqDto){
        Response<MemberDto> response = new Response<>();
        Member member = memberService.saveMember(reqDto);
        response.setResult(MemberMapper.mapToDto(member));
        response.setMessage("Created Member Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MemberDto>> findMemberByid(@Valid @PathVariable Long id){
        Response<MemberDto> response = new Response<>();
        Member member = memberService.findMemberById(id);
        response.setResult(MemberMapper.mapToDto(member));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<MemberDto>>> findAllMembers(){
        Response<List<MemberDto>> response = new Response<>();
        List<Member> memberList = memberService.findAllMembers();
        response.setResult(memberList
                .stream()
                .map(MemberMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<Response<List<MemberDto>>> findAllMembersBySearch(@PathVariable("searchTerm") String searchTerm){
        Response<List<MemberDto>> listResponse = new Response<>();
        List<Member> memberList = memberService.searchMembers(searchTerm);
        listResponse.setResult(memberList
                .stream()
                .map(MemberMapper::mapToDto)
                .toList());
        return ResponseEntity.ok(listResponse);
    }
}
