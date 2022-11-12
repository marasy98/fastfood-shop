package com.example.fast_food.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"PROMOTION_ID","PRODUCT_ID"})})
public class PromotionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMOTIONPRODUCT_ID", nullable = false)
    private Long promotionProductId;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "PROMOTION_ID")
    private Promotion promotion;
}
