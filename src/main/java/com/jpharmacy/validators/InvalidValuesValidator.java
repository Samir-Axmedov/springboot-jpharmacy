package com.jpharmacy.validators;

import com.jpharmacy.validators.annotations.InvalidValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InvalidValuesValidator implements ConstraintValidator<InvalidValues, String> {

    public InvalidValues constraint;

    public void initialize(InvalidValues constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(value != null) {
            if (constraint.ignoreCase() == false) {
                return Arrays.stream(constraint.values()).filter(x -> x.equals(value)).findFirst().isPresent() == false;
            } else {
                return Arrays.stream(constraint.values()).filter(x -> x.toLowerCase().equals(value.toLowerCase())).findFirst().isPresent() == false;
            }
        }

        return true;
    }

}