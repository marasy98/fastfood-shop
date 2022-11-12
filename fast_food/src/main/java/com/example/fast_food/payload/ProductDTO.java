package com.example.fast_food.payload;

import lombok.Data;

@Data
public class ProductDTO {
    String description;
    String category;
    String name;
    int price;
}
