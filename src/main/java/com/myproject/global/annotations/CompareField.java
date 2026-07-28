package com.myproject.global.annotations;

import com.myproject.global.validation.CompareFieldsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {CompareFieldsValidator.class}
)
@Repeatable(CompareFields.class)
public @interface CompareField {
    String message() default "{vn.etc.customs.lib.validation.CompareField.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String baseField();

    String matchField();

    boolean isIgnoreCase() default false;

    boolean isNot() default false;

    boolean isTrim() default false;
}
