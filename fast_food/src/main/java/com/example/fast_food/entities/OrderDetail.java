package com.example.fast_food.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"INVOICE_ID","PRODUCT_ID"})})
public class OrderDetail {
    @Id
    @Column(name = "ORDERDETAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;
    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
