package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.MemberDto;
import com.example.appgcm.models.enums.RoleType;
import com.example.appgcm.repositories.UserRepository;
import com.example.appgcm.services.UserService;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;



    @Override
    public List<AppUser> findAllMembers() {
        List<AppUser> memberList = userRepository.findAll();
        if (memberList.isEmpty()) {
            throw new IllegalArgumentException("Not Found Any Member");
        }
        return memberList;
    }

    @Override
    public List<AppUser> searchMembers(String searchTerm) {
        List<AppUser> memberList = userRepository.searchMembers(searchTerm);
        if (memberList.isEmpty()) {
            throw new IllegalArgumentException("Not Found Any Member");
        }
        return memberList;
    }

    @Override
    public AppUser findMemberById(Long id) {
        Optional<AppUser> member = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found Member " + id)));
        return member.get();
    }

    @Override
    public AppUser saveMember(MemberDto member) {
        // Check member
        Optional<AppUser> memberOptional = userRepository.findByIdentityNumber(member.identityNumber());
        if(memberOptional.isPresent()){
            throw new IllegalArgumentException("Member deja exist!");
        }

        // Add member
        AppUser member1 = AppUser.builder()
                .fullName(member.firstName())
                .accessionDate(LocalDate.now())
                .nationality(member.nationality())
                .identityDocumentType(member.identityDocumentType())
                .identityNumber(member.identityNumber())
                .build();
        return userRepository.save(member1);
    }

    @Override
    public AppUser register(AppUser req) {
        AppUser user = AppUser.builder()
                .userName(req.getUsername())
                .email(req.getEmail())
                .fullName(req.getFullName())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        roleRepository.findRoleByName(RoleType.MEMBER).ifPresent(role -> user.setRoles(Set.of(role)));
        return userRepository.save(user);
    }

    @Override
    public AppUser authenticate(AppUser req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        return userRepository.findByEmailOrUserName(req.getEmail(), req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Not Found Any User"));
    }


}
