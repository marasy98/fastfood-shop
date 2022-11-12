package com.example.fast_food.service;

import com.example.fast_food.payload.AccountDTO;
import com.example.fast_food.payload.RegisterDTO;
import com.example.fast_food.payload.UpdateDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.text.ParseException;


public interface AccountService extends UserDetailsService {
    UserDetails loadUserById(Long userId);
    Boolean save(RegisterDTO registerRequest) throws IOException;

    AccountDTO getProfile(long id);

    void updateProfile(UpdateDTO updateDTO) throws ParseException;

    Boolean checkEmailExist(String email);
}
