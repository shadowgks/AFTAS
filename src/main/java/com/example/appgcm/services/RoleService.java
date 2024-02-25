package com.example.appgcm.services;

import com.example.appgcm.models.entity.Role;
import com.example.appgcm.models.enums.RoleType;

public interface RoleService {
    Role switchRole(RoleType roleType);
}
