package com.example.fast_food.payload;

import com.example.fast_food.config.*;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {

    @Size(min = 3, max = 10)
    private String name;
    @NotBlank
    @Email
    @ExistEmailContraint
    private String email;


    @ValidPassword
    private String password;
    @DateConstraint
    private String date;
    private MultipartFile multipartFile;
    @NotBlank
    private String fullname;
    @ContactNumberConstraint
    private String phone;

    @ValidPassword
    private String confirmPassword;
}
