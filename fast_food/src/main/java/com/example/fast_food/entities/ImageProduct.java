package com.example.fast_food.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageProduct {
    @Id
    @Column(name = "IMAGEPRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageProductId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonBackReference
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID",unique = true)
    private Product product;
    private String imageName;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
}
