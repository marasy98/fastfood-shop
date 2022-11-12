package com.example.fast_food.service.impl;

import com.example.fast_food.config.CustomUserDetails;
import com.example.fast_food.entities.*;
import com.example.fast_food.payload.AccountDTO;
import com.example.fast_food.payload.RegisterDTO;
import com.example.fast_food.payload.UpdateDTO;
import com.example.fast_food.repositories.AccountRepository;
import com.example.fast_food.service.AccountService;
import com.example.fast_food.service.RoleService;
import com.example.fast_food.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        // Kiểm tra xem user có tồn tại trong database không?
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(account);
    }

    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(account);
    }


    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean save(RegisterDTO registerRequest) throws IOException {
        Boolean value = true;
        ModelMapper modelMapper = new ModelMapper();

        Account account = modelMapper.map(registerRequest, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        ImageAccount imageData = ImageAccount.builder()
                .account(account)
                .imageName(registerRequest.getEmail())
                .type(registerRequest.getMultipartFile().getContentType())
                .imageData(ImageUtils.compressImage(registerRequest.getMultipartFile().getBytes())).build();
        account.setImageAccount(imageData);

        Roles role = roleService.findByRoleName("ROLE_USER");
        if (role.getId() == 0) {
            role.setRoleName("ROLE_USER");
        }
        account.setAccountRoles(Arrays.asList(new AccountRole(account, role)));
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            value = false;
            return value;
        }
        return value;
    }

    @Override
    public AccountDTO getProfile(long id) {
        Account account = accountRepository.findByAccountId(id);
        ModelMapper modelMapper = new ModelMapper();
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        return accountDTO;
    }

    @Override
    public void updateProfile(UpdateDTO updateDTO) throws ParseException {

            Date date= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(updateDTO.getDate());

        accountRepository.updateProfile(updateDTO.getFullname(), updateDTO.getName(),date , updateDTO.getPhone(), updateDTO.getAccountId());
    }

    @Override
    public Boolean checkEmailExist(String email) {
        Account account=accountRepository.findByEmail(email);

        return account==null?false:true;
    }
}
