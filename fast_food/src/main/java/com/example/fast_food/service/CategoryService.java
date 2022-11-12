package com.example.fast_food.service;

import com.example.fast_food.entities.Category;
import com.example.fast_food.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void addCategory(String categoryName);
    void updateCategory(long id,String categoryName);
    List<CategoryResponse> findAll();
    void deleteCategory(long id);
}
