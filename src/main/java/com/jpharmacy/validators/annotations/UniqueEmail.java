package com.jpharmacy.validators.annotations;

import com.jpharmacy.validators.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Ten e-mail jest już przypisany do innego konta.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
