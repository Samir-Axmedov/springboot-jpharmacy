package com.jpharmacy.validators.annotations;

import com.jpharmacy.validators.InvalidPhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvalidPhoneValidator.class)
public @interface InvalidPhone {
    String message() default "Prawidłowy format numeru telefonu: 333444555";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
