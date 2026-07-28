package com.myproject.global.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.myproject.global.annotations.EqualField;
import com.myproject.global.exception.InternalServerException;
import com.myproject.global.util.ReflectEx;
import org.apache.commons.lang3.StringUtils;

public class EqualFieldsValidator implements ConstraintValidator<EqualField, Object> {
    private String baseField;
    private String matchField;
    private boolean isIgnoreCase;
    private boolean isNot;
    private boolean isTrim;

    public EqualFieldsValidator() {
    }

    public void initialize(EqualField constraint) {
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
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate().replace(".EqualField", ".NotEqualField")).addConstraintViolation();
            }

            Object baseFieldValue = ReflectEx.getFieldValueToStringEx(object, this.baseField);
            Object matchFieldValue = ReflectEx.getFieldValueToStringEx(object, this.matchField);
            if (baseFieldValue == matchFieldValue) {
                return this.isNot != Boolean.TRUE;
            } else if (baseFieldValue == null) {
                return this.isNot != Boolean.FALSE;
            } else {
                if (this.isTrim && baseFieldValue.getClass().equals(String.class)) {
                    baseFieldValue = StringUtils.trim((String)baseFieldValue);
                }

                if (this.isTrim && matchFieldValue.getClass().equals(String.class)) {
                    matchFieldValue = StringUtils.trim((String)matchFieldValue);
                }

                if (this.isIgnoreCase && baseFieldValue.getClass().equals(String.class)) {
                    return this.isNot != ((String)baseFieldValue).equalsIgnoreCase((String)matchFieldValue);
                } else {
                    return this.isNot != baseFieldValue.equals(matchFieldValue);
                }
            }
        } catch (Exception var5) {
            throw new InternalServerException(var5);
        }
    }
}
