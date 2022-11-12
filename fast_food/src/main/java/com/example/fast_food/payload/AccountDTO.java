package com.example.fast_food.payload;

import lombok.Data;

import java.sql.Date;
@Data
public class AccountDTO {
    private Long accountId;
    private String name;
    private String email;
    private Date date;
    private String fullname;
    private String phone;
}
