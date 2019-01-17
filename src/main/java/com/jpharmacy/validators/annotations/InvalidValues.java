package com.jpharmacy.validators.annotations;

import com.jpharmacy.validators.InvalidValuesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvalidValuesValidator.class)
public @interface InvalidValues {
    String message() default "Illegal names {names}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    //moje atrybuty i ich domyślne wartości
    String[] values() default {};
    boolean ignoreCase() default false;

}
