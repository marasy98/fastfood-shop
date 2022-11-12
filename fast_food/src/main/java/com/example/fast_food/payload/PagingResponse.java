package com.example.fast_food.payload;

import lombok.Data;

@Data
public class PagingResponse {
    private long id;
    private int quantity;
    private String description;
    private String categoryName;
    private String productName;
    private int price;
    private String imageName;
}
