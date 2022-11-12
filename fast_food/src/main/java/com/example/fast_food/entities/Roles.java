package com.example.fast_food.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Roles {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<AccountRole> accountRoles=new ArrayList<>();

}
