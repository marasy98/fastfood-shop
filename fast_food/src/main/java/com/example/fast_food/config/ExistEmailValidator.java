package com.example.fast_food.config;

import com.example.fast_food.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExistEmailValidator implements
        ConstraintValidator<ExistEmailContraint, String> {
@Autowired
    private AccountService accountService;
    @Override
    public void initialize(ExistEmailContraint existEmailContraint) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {

       return !accountService.checkEmailExist(email);
    }

}