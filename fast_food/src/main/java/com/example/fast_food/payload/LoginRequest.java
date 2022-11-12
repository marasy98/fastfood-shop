package com.example.fast_food.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotBlank()
    private String email;


    @Size(min = 8,max = 16)
    private String password;
}
