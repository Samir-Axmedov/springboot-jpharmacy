package com.jpharmacy.validators;

import com.jpharmacy.validators.annotations.InvalidPhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InvalidPhoneValidator implements ConstraintValidator<InvalidPhone, String> {

    @Override
    public void initialize(InvalidPhone constraintAnnotation) {

    }
    @Override
    public boolean isValid(String userPhone, ConstraintValidatorContext constraintValidatorContext) {
        if(userPhone.matches("(^$|[0-9]{9})")){
            return true;
        }else {
            return false;
        }

    }
}
