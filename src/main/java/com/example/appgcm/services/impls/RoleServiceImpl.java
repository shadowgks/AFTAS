package com.example.appgcm.services.impls;

import com.example.appgcm.models.entity.Competition;
import com.example.appgcm.models.entity.Role;
import com.example.appgcm.models.enums.RoleType;
import com.example.appgcm.repositories.RoleRepository;
import com.example.appgcm.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()){
            throw new IllegalArgumentException("Not Found list Any Competitions");
        }
        return roles;
    }
}
