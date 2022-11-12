package com.example.fast_food.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARTITEM_ID")
    private Long cartItemId;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    private int quantity;
}
