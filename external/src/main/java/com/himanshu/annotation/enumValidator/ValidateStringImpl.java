package com.himanshu.annotation.enumValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateStringImpl implements ConstraintValidator<ValidateString, String> {

    private List<String> validMethods;

    @Override
    public void initialize(ValidateString constraintAnnotation) {
        validMethods = new ArrayList<String>();
        for(String val : constraintAnnotation.acceptedValues()) {
            validMethods.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String method, ConstraintValidatorContext context) {

        return validMethods.contains(method.toUpperCase());
    }

}
