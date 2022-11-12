package com.example.fast_food.controller;

import com.example.fast_food.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DashBoardCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategory")
    public ResponseEntity findListCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/updateCategory")
    public void updateCategory(@RequestParam(value = "id") long id,@RequestParam(value = "categoryName") String categoryName) {

        categoryService.updateCategory(id,categoryName);
    }
    @PostMapping("/addCategory")
    public void addCategory(@RequestParam(value = "categoryname", required = false) String categoryName) {

        categoryService.addCategory(categoryName);
    }

    @DeleteMapping ("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable long id) {

        categoryService.deleteCategory(id);
    }
}
