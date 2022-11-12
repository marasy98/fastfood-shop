package com.example.fast_food.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMOTION_ID", nullable = false)
    private Long promotionId;
    @OneToMany(mappedBy = "promotion",cascade = CascadeType.ALL)
    private List<PromotionProduct> promotionProducts=new ArrayList<>();
}
