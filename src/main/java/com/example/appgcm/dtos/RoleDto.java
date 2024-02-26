package com.example.appgcm.dtos;

import com.example.appgcm.models.entity.Permission;
import com.example.appgcm.models.enums.RoleType;
import jakarta.persistence.*;

import java.util.Set;

public record RoleDto(
        Long id,
        RoleType name,
        Set<Permission> permissions
) { }
