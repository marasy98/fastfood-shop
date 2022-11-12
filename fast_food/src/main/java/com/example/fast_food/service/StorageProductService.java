package com.example.fast_food.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageProductService {
    public String uploadImage(MultipartFile file,String name) throws IOException;
    public void updateImage(MultipartFile file,String name) throws IOException;
    public byte[] downloadImage(String fileName);
    public List<String> getName();
}
