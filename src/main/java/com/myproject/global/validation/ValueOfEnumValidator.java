package com.myproject.global.validation;

import com.myproject.global.annotations.ValueIn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValueOfEnumValidator implements ConstraintValidator<ValueIn, Integer> {

    private int[] allowedValues;

    @Override
    public void initialize(ValueIn constraintAnnotation) {
        this.allowedValues = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // để @NotNull xử lý riêng nếu cần bắt buộc
        }
        return Arrays.stream(allowedValues).anyMatch(v -> v == value);
    }
}
