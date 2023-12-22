package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Member;
import com.example.appgcm.repositories.MemberRepository;
import com.example.appgcm.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        if (memberList.isEmpty()) {
            throw new IllegalArgumentException("Not Found Any Member");
        }
        return memberList;
    }

    @Override
    public List<Member> searchMembers(String searchTerm) {
        List<Member> memberList = memberRepository.searchMembers(searchTerm);
        if (memberList.isEmpty()) {
            throw new IllegalArgumentException("Not Found Any Member");
        }
        return memberList;
    }

    @Override
    public Member findMemberById(Long id) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found Member " + id)));
        return member.get();
    }

    @Override
    public Member saveMember(MemberDto member) {
        // Check member
        Optional<Member> memberOptional = memberRepository.findByIdentityNumber(member.identityNumber());
        if(memberOptional.isPresent()){
            throw new IllegalArgumentException("Member deja exist!");
        }

        // Add member
        Member member1 = Member.builder()
                .firstName(member.firstName())
                .lastName(member.lastName())
                .accessionDate(LocalDate.now())
                .nationality(member.nationality())
                .identityDocumentType(member.identityDocumentType())
                .identityNumber(member.identityNumber())
                .build();
        return memberRepository.save(member1);
    }


}
