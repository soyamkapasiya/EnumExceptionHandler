package com.kapasiya.em.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidators implements ConstraintValidator<EnumValidator, String> {

    private Enum<?>[] enumConstants;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        this.enumConstants = constraintAnnotation.target().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (Enum<?> enumConstant : enumConstants) {
            if (enumConstant.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
