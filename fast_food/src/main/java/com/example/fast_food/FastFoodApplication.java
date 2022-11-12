package com.example.fast_food;

import com.example.fast_food.payload.RegisterDTO;
import com.example.fast_food.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FastFoodApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FastFoodApplication.class, args);


    }
    @Autowired
    AccountService accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

    }

//    @Override
//    public void run(String... args) throws Exception {
//        RegisterDTO account=new RegisterDTO();
//        account.setEmail("loda@gmail.com");
//        account.setPassword("Loda123456");
//        account.setName("linh");
//        accountRepository.save(account);
//    }
}
