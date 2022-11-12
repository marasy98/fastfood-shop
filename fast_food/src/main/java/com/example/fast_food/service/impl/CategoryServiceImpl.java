package com.example.fast_food.service.impl;

import com.example.fast_food.entities.Category;
import com.example.fast_food.payload.CategoryResponse;
import com.example.fast_food.repositories.CategoryRepository;
import com.example.fast_food.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void addCategory(String categoryName) {
        Category category=new Category();
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(long id, String categoryName) {
        categoryRepository.updateCategory(id,categoryName);
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<CategoryResponse> categoryResponseList=new ArrayList<>();
        List<Category> list= categoryRepository.findAll();
        for (int i=0;i<list.size();i++){
            CategoryResponse categoryResponse=new CategoryResponse();
            categoryResponse.setCategoryName(list.get(i).getCategoryName());
            categoryResponse.setId(list.get(i).getCategoryId());
            categoryResponseList.add(categoryResponse);
        }
        return categoryResponseList;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteByCategoryId(id);
    }
}
