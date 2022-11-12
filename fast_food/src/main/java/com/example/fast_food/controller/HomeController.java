package com.example.fast_food.controller;

import com.example.fast_food.entities.Product;
import com.example.fast_food.payload.ProductDTO;
import com.example.fast_food.repositories.ProductRepository;
import com.example.fast_food.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private StorageProductService service;

    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable long id){
        Product product= productRepository.findByProductId(id);
        ProductDTO productDTO=new ProductDTO();
        productDTO.setCategory(product.getCategory().getCategoryName());
        productDTO.setName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }
}
