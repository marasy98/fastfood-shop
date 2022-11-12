package com.example.fast_food.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Invoice {
    @Id
    @Column(name = "INVOICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails=new ArrayList<>();
}
