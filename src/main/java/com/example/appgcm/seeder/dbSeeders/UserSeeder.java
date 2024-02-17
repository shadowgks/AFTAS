package com.example.appgcm.seeder.dbSeeders;

import com.example.appgcm.models.entity.AppUser;
import com.example.appgcm.models.entity.Permission;
import com.example.appgcm.models.entity.Role;
import com.example.appgcm.models.enums.ActionType;
import com.example.appgcm.models.enums.IdentityDocumentType;
import com.example.appgcm.models.enums.RoleType;
import com.example.appgcm.repositories.PermissionRepository;
import com.example.appgcm.repositories.RoleRepository;
import com.example.appgcm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Set<String> subjects = Set.of(
            "user",
            "competition",
            "member",
            "hunting",
            "ranking"
    );

    public void seed(){
        Set<Permission> permissions = new HashSet<>();
        if (permissionRepository.findAll().isEmpty()) {
            subjects.forEach(subject -> {
                for (ActionType actionType : ActionType.values()) {
                    Permission permission = Permission.builder()
                            .action(actionType)
                            .subject(subject.toUpperCase())
                            .build();
                    permissions.add(permission);
                }
            });
            permissionRepository.saveAll(permissions);
        }


        if (roleRepository.findAll().isEmpty()){
            for (RoleType roleType : RoleType.values()) {
                if (roleType.equals(RoleType.ADMIN)){
                    Role role = Role.builder()
                            .name(roleType)
                            .permissions(permissions.stream().filter(p -> p.getAction().equals(ActionType.ALL)).collect(Collectors.toSet()))
                            .build();
                    roleRepository.save(role);
                }
                if(roleType.equals(RoleType.MEMBER)){
                    Role role = Role.builder()
                            .name(roleType)
                            .permissions(permissions.stream().filter(p -> p.getAction().equals(ActionType.READ)).collect(Collectors.toSet()))
                            .build();
                    roleRepository.save(role);
                }
            }

            if(userRepository.findAll().isEmpty()){
                AppUser user = AppUser.builder()
                        .fullName("saad moumou")
                        .email("saad@admin.com")
                        .userName("saadmomo")
                        .identityNumber("ha234567")
                        .nationality("maroc")
                        .identityDocumentType(IdentityDocumentType.CIN)
                        .password(passwordEncoder.encode("12345678999"))
                        .build();
                roleRepository.findRoleByName(RoleType.ADMIN).ifPresent(role -> user.setRoles(Set.of(role)));
                userRepository.save(user);
            }
        }
    }
}
