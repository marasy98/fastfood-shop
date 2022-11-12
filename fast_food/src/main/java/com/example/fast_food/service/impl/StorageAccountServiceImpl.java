package com.example.fast_food.service.impl;

import com.example.fast_food.entities.ImageAccount;
import com.example.fast_food.entities.ImageProduct;
import com.example.fast_food.repositories.StorageAccountRepository;
import com.example.fast_food.repositories.StorageProductRepository;
import com.example.fast_food.service.StorageAccountService;
import com.example.fast_food.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service
public class StorageAccountServiceImpl implements StorageAccountService {
    @Autowired
    private StorageAccountRepository repository;

    public byte[] downloadImage(String fileName) {
        Optional<ImageAccount> dbImageData = repository.findByImageName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    @Override
    public void updateImageProfile(MultipartFile file,long id) throws IOException {

        repository.updateImage(ImageUtils.compressImage(file.getBytes()), file.getContentType(), id);
    }
}
