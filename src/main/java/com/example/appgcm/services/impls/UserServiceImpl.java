package com.example.appgcm.services.impls;

import com.example.appgcm.dtos.UserDto.Req.MemberReqDto;
import com.example.appgcm.dtos.UserDto.Req.RoleReqDTO;
import com.example.appgcm.models.entity.RefreshToken;
import com.example.appgcm.models.entity.Role;
import com.example.appgcm.models.enums.RoleType;
import com.example.appgcm.repositories.UserRepository;
import com.example.appgcm.services.RefreshTokenService;
import com.example.appgcm.services.UserService;
import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;



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
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found Member " + id));
    }

    @Override
    public AppUser saveMember(MemberReqDto reqDto) {
        // Check member
        Optional<AppUser> memberOptional = userRepository.findByIdentityNumber(reqDto.registerReqDTO().identityNumber());
        if(memberOptional.isPresent()){
            throw new IllegalArgumentException("Member deja exist!");
        }

        // Add member
        AppUser member1 = AppUser.builder()
                .fullName(reqDto.registerReqDTO().fullName())
                .accessionDate(LocalDate.now())
                .nationality(reqDto.registerReqDTO().nationality())
                .identityDocumentType(reqDto.registerReqDTO().identityDocumentType())
                .identityNumber(reqDto.registerReqDTO().identityNumber())
                .isWorking(true)
                .build();
        return userRepository.save(member1);
    }

    @Override
    public AppUser updateUser(String identityNumber, AppUser req) {
        AppUser userFound = userRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new IllegalArgumentException("Not Found this user "+ identityNumber));

        // Extract RoleType from each Role in req.getRoles()
        Set<RoleType> roleTypes= req.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        AppUser user = AppUser.builder()
                .id(userFound.getId())
                .userNamee(userFound.getUserNamee())
                .fullName(userFound.getFullName())
                .identityDocumentType(userFound.getIdentityDocumentType())
                .accessionDate(userFound.getAccessionDate())
                .email(userFound.getEmail())
                .identityNumber(userFound.getIdentityNumber())
                .password(userFound.getPassword())
                .isWorking(req.getIsWorking())
                .build();

        roleTypes.forEach(r -> roleRepository.findRoleByName(r).ifPresent(role -> user.setRoles(Set.of(role))));
        return userRepository.save(user);
    }

    @Override
    public Object authContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

    @Override
    public AppUser register(AppUser req) {
        AppUser user = AppUser.builder()
                .userNamee(req.getUserNamee())
                .email(req.getEmail())
                .nationality(req.getNationality())
                .fullName(req.getFullName())
                .identityDocumentType(req.getIdentityDocumentType())
                .identityNumber(req.getIdentityNumber())
                .password(passwordEncoder.encode(req.getPassword()))
                .accessionDate(LocalDate.now())
                .isWorking(false)
                .build();
        roleRepository.findRoleByName(RoleType.ADHERENT).ifPresent(role -> user.setRoles(Set.of(role)));
        userRepository.save(user);
        refreshTokenService.createRefreshToken(user.getId());
        return user;
    }

    @Override
    public AppUser authenticate(AppUser req) {
        AppUser user = userRepository.findByEmailOrUserNamee(req.getEmail(), req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Not Found Any User"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        refreshTokenService.createRefreshToken(user.getId());
        return user;
    }


}
