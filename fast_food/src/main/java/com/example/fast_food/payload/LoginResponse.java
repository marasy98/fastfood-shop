package com.example.fast_food.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String role;
    private long id;

    public LoginResponse(String accessToken, String role,long id) {
        this.accessToken = accessToken;
        this.role = role;
        this.id=id;
    }
}
