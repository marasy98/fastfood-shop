package com.example.fast_food.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ACCOUNT_ID","role_id"})})
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    @JsonBackReference
    private Account account;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Roles role;

    public AccountRole(Account account, Roles role) {
        this.account=account;
        this.role=role;
    }
}
