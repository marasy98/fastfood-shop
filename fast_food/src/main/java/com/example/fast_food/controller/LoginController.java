package com.example.fast_food.controller;

import com.example.fast_food.config.CustomUserDetails;

import com.example.fast_food.entities.Account;
import com.example.fast_food.payload.*;
import com.example.fast_food.jwt.JwtTokenProvider;
import com.example.fast_food.repositories.AccountRepository;
import com.example.fast_food.repositories.ProductRepository;
import com.example.fast_food.repositories.StorageProductRepository;
import com.example.fast_food.service.AccountService;
import com.example.fast_food.service.CategoryService;
import com.example.fast_food.service.StorageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity register(@Valid RegisterDTO registerRequest,BindingResult bindingResult) throws IOException {

        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                String errorMessage;
                 if(((FieldError) error).getField().equals("multipartFile")){
                    errorMessage="Invalid image";
                }else{
                    errorMessage = error.getDefaultMessage();
                }
                String fieldName = ((FieldError) error).getField();

                errors.put(fieldName,errorMessage);
            });
            return new ResponseEntity<>( errors,HttpStatus.BAD_REQUEST);
        }
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return new ResponseEntity<>("Password", HttpStatus.FORBIDDEN);
        }
        accountService.save(registerRequest);
            return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//        List<AccountRole> accountRoles = accountRepository.findByEmail(((String) authentication.getPrincipal()).getUsername()).getAccountRoles().stream().filter(m -> m.getRole().getRoleName().equals("ROLE_ADMIN")).collect(Collectors.toList());
        Account account = accountRepository.findByEmail(((CustomUserDetails) authentication.getPrincipal()).getUsername());
        String role = "";
        role = account.getAccountRoles().get(0).getRole().getRoleName();

        return new ResponseEntity<>(new LoginResponse(jwt, role, account.getAccountId()), HttpStatus.OK);
    }

    //     Api /api/random yêu cầu phải xác thực mới có thể request
    @Autowired
    private StorageProductService service;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("loi ex");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @Autowired
    StorageProductRepository storageRepository;


    @PostMapping("/checkJWT_ADMIN")
    public String checkAdmin() {
        return "JWT Hợp lệ mới có thể thấy được message này";
    }

    @PostMapping("/checkJWT_USER")
    public String checkUser() {
        return "JWT Hợp lệ mới có thể thấy được message này";
    }


}
