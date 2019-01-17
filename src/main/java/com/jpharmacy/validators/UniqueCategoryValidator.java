package com.jpharmacy.validators;

import com.jpharmacy.validators.annotations.UniqueCategory;
import com.jpharmacy.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void initialize(UniqueCategory constraintAnnotation) {

    }

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        return categoryService == null || (categoryName != null && categoryService.isUniqueCategory(categoryName));

    }
}
