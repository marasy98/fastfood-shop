package com.example.fast_food.service.impl;

import com.example.fast_food.entities.ImageProduct;
import com.example.fast_food.entities.Product;
import com.example.fast_food.repositories.ProductRepository;
import com.example.fast_food.repositories.StorageProductRepository;
import com.example.fast_food.service.StorageProductService;
import com.example.fast_food.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StorageProductServiceImpl implements StorageProductService {
    @Autowired
    private StorageProductRepository repository;
    @Autowired
    private ProductRepository productRepository;

    public String uploadImage(MultipartFile file, String name) throws IOException {
        Product product=productRepository.findByProductName(name);

        ImageProduct imageData = ImageProduct.builder()
                .imageName(name)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build();
        product.setImageProduct(imageData);
        imageData.setProduct(product);
        ImageProduct imageDataSave = repository.save(imageData);
        if (imageDataSave != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, String name) throws IOException {
        repository.updateImage(file.getContentType(),ImageUtils.compressImage(file.getBytes()),name);
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageProduct> dbImageData = repository.findByImageName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    @Override
    public List<String> getName() {
        return repository.findAllName();
    }
}
