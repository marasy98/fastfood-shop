package com.example.fast_food.service;

import com.example.fast_food.entities.Category;

public interface ProductService {
    void updateProduct(long categoryId, int price, String description, String name,int quantity);


}
