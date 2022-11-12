package com.example.fast_food.config;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented

@Constraint(validatedBy = PasswordConstraintValidator.class)

@Target({ TYPE, FIELD, ANNOTATION_TYPE })

@Retention(RUNTIME)

public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}