package com.example.fast_food.service;

import com.example.fast_food.entities.Roles;

public interface RoleService {
    Roles findByRoleName(String roleName);
}
