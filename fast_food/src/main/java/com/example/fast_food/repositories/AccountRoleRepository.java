package com.example.fast_food.repositories;

import com.example.fast_food.entities.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole,Integer> {
}
