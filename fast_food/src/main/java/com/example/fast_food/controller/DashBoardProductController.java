package com.example.fast_food.controller;

import com.example.fast_food.entities.Product;
import com.example.fast_food.payload.EditProductRequest;
import com.example.fast_food.repositories.CategoryRepository;
import com.example.fast_food.repositories.ProductRepository;
import com.example.fast_food.repositories.StorageProductRepository;
import com.example.fast_food.service.ProductService;
import com.example.fast_food.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DashBoardProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StorageProductRepository storageRepository;

//    @PostMapping("/upProduct")
//    public ResponseEntity<String> upProduct(@Valid @RequestBody ProductRequest productRequest) {
//
//        Product product = new Product();
//        product.setProductName(productRequest.getName());
//        product.setDescription(productRequest.getDescription());
//        product.setPrice(productRequest.getPrice());
//        category.setCategoryName(productRequest.getCategory());
//        product.setCategory(category);
//        List<Product> list = new ArrayList<>();
//        list.add(product);
//        category.setProducts(list);
//        Category categorySave = categoryRepository.save(category);
//        if (categorySave != null) {
//            return ResponseEntity.status(HttpStatus.OK).body("ok");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not ok");
//    }

    @DeleteMapping("/product/{name}")
    public ResponseEntity delete(@PathVariable("name") String name) {
        storageRepository.deleteByImageName(name);
        productRepository.deleteByProductName(name);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @Autowired
    private StorageProductService service;
    @Autowired
    private ProductService productService;
    @PutMapping(value ="/editProduct",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateProduct(@Valid EditProductRequest editProductRequest) throws IOException {
        productService.updateProduct( editProductRequest.getCategoryId(), editProductRequest.getPrice(),editProductRequest.getDescription(),editProductRequest.getProductName(),editProductRequest.getQuantity());
        if(!editProductRequest.getFile().isEmpty()){
            service.updateImage(editProductRequest.getFile(),editProductRequest.getProductName());
        }
    }
    @PostMapping(value = "/addProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addProduct(EditProductRequest editProductRequest) throws IOException {
        Product product = new Product(editProductRequest.getDescription(), editProductRequest.getProductName(), editProductRequest.getPrice(), editProductRequest.getFile(), editProductRequest.getCategoryId(), editProductRequest.getQuantity());

        productRepository.save(product);

    }
}
