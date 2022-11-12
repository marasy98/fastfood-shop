package com.example.fast_food.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class EditProductRequest {
    private int quantity;
    private String description;
    private String productName;
    private int price;
    private long categoryId;
    private MultipartFile file;
}
