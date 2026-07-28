package com.myproject.global.annotations;

import com.myproject.global.validation.ValueOfEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueIn {
    String message() default "Giá trị không hợp lệ";

    int[] values();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
