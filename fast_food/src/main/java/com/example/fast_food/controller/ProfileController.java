package com.example.fast_food.controller;

import com.example.fast_food.payload.AccountDTO;
import com.example.fast_food.service.AccountService;
import com.example.fast_food.service.StorageAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    private StorageAccountService service;
    @Autowired
    private AccountService accountService;



    @GetMapping("/getInfoAccount/{id}")
    public ResponseEntity<AccountDTO> getInformationOfAccount(@PathVariable long id) {
        AccountDTO accountDTO=accountService.getProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

    @GetMapping("/imageAccount/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
    @PutMapping("/updateImageProfile")
    public void updateImageProfile(@RequestParam MultipartFile file,@RequestParam long id) throws IOException {
        service.updateImageProfile(file,id);
    }
@PutMapping("/updateProfile")
    public void updateProfile(@RequestBody @Valid AccountDTO accountDTO){

}

}
