package com.example.fast_food.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageAccountService {

    public byte[] downloadImage(String fileName);

    void updateImageProfile(MultipartFile file,long id) throws IOException;
}
