package com.example.fast_food.service.impl;

import com.example.fast_food.entities.Roles;
import com.example.fast_food.repositories.RoleRepository;
import com.example.fast_food.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Roles findByRoleName(String roleName) {
        Optional<Roles> role=roleRepository.findByRoleName(roleName);
        return role.orElse(new Roles());
    }
}
