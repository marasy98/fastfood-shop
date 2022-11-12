package com.example.fast_food.entities;

import com.example.fast_food.config.ContactNumberConstraint;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "account", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private ImageAccount imageAccount;
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    private Date date;
    private String fullname;

    private String phone;

    @Column(name = "REGISTER_DATE")
    private LocalDate registerDate;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<AccountRole> accountRoles = new ArrayList<>();
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();
    @OneToOne(mappedBy = "account", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private Cart cart;
}
