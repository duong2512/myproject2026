package com.myproject.global.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myproject.global.annotations.CompareField;
import com.myproject.global.exception.InternalServerException;
import com.myproject.global.util.ReflectEx;
import org.apache.commons.lang3.StringUtils;

public class CompareFieldsValidator implements ConstraintValidator<CompareField, Object> {
    private String baseField;
    private String matchField;
    private boolean isIgnoreCase;
    private boolean isNot;
    private boolean isTrim;

    public CompareFieldsValidator() {
    }

    public void initialize(CompareField constraint) {
        this.baseField = constraint.baseField();
        this.matchField = constraint.matchField();
        this.isIgnoreCase = constraint.isIgnoreCase();
        this.isNot = constraint.isNot();
        this.isTrim = constraint.isTrim();
    }

    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            if (this.isNot) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate().replace(".CompareField", ".NotCompareField")).addConstraintViolation();
            }

            String baseFieldValue = ReflectEx.getFieldValueToStringEx(object, this.baseField);
            String matchFieldValue = ReflectEx.getFieldValueToStringEx(object, this.matchField);
            if (baseFieldValue == null) {
                return this.isNot != Boolean.FALSE;
            } else if (baseFieldValue.equals(matchFieldValue)) {
                return this.isNot != Boolean.TRUE;
            } else {
                if (matchFieldValue == null) {
                    matchFieldValue = "";
                }

                if (this.isTrim) {
                    baseFieldValue = StringUtils.trim(baseFieldValue);
                    matchFieldValue = StringUtils.trim(matchFieldValue);
                }

                if (this.isIgnoreCase && baseFieldValue.getClass().equals(String.class)) {
                    return this.isNot != baseFieldValue.toLowerCase().contains(matchFieldValue.toLowerCase());
                } else {
                    return this.isNot != baseFieldValue.contains(matchFieldValue);
                }
            }
        } catch (Exception var5) {
            throw new InternalServerException(var5);
        }
    }
}
