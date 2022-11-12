package com.example.fast_food.service.impl;

import com.example.fast_food.entities.Category;
import com.example.fast_food.repositories.ProductRepository;
import com.example.fast_food.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;



    @Override
    public void updateProduct(long categoryId, int price, String description, String name,int quantity) {
        productRepository.updateProduct(categoryId,price,description,name,quantity);
    }


}
