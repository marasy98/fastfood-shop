package com.example.fast_food.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateValidator implements
        ConstraintValidator<DateConstraint, String> {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    @Override
    public void initialize(DateConstraint dateConstraint) {
    }

    @Override
    public boolean isValid(String customDateField,
                           ConstraintValidatorContext cxt) {

        try
        {
            Date date1 = new SimpleDateFormat(DATE_PATTERN).parse(customDateField);
            LocalDate localDate= date1.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate today = LocalDate.now();
            System.out.println("dai2"+localDate.isBefore(today)+"  "+localDate.toString()+" "+today.toString());
            if(localDate.isAfter(today)){
                System.out.println("dai");
                cxt.buildConstraintViolationWithTemplate("The date of birth must not exceed the current time")

                        .addConstraintViolation()

                        .disableDefaultConstraintViolation();
                return false;
            }
            return true;
        }
        catch (ParseException e)
        {

            return false;
        }
    }

}