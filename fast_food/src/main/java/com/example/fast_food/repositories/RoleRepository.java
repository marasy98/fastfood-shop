package com.example.fast_food.repositories;

import com.example.fast_food.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoleName(String username);
}
