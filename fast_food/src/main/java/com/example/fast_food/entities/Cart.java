package com.example.fast_food.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long cartId;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems=new ArrayList<>();
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonBackReference
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID",unique = true)
    private Account account;
}
