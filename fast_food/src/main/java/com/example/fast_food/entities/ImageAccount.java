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
public class ImageAccount {
    @Id
    @Column(name = "IMAGEACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageAccountId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonBackReference
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID",unique = true)
    private Account account;
    private String imageName;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
}
