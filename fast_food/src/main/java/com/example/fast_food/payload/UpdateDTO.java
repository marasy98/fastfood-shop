package com.example.fast_food.payload;

import com.example.fast_food.config.ContactNumberConstraint;
import com.example.fast_food.config.DateConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
@Data
public class UpdateDTO {
    @NotNull
    private Long accountId;
    @NotBlank
    @Size(min = 3, max = 10)
    private String name;
    @DateConstraint
    private String date;
    @NotBlank
    private String fullname;
    @ContactNumberConstraint
    private String phone;
}
