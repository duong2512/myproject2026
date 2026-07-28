package com.myproject.global.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        Class<?> targetClass = ex.getBindingResult().getTarget() != null
                ? ex.getBindingResult().getTarget().getClass()
                : null;

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String message = buildMessage(error, targetClass);
            errors.add(message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private String buildMessage(FieldError error, Class<?> targetClass) {
        String fieldName = error.getField();
        String label = getFieldLabel(targetClass, fieldName);
        String annotationType = error.getCode(); // "NotEmpty", "NotNull", "Size", "ValueIn"...

        // Nếu annotation đã có message tùy chỉnh (không phải message mặc định) -> giữ nguyên
        String defaultMessage = error.getDefaultMessage();
        boolean isDefaultTemplate = defaultMessage != null &&
                (defaultMessage.contains("must not be") || defaultMessage.contains("must not be null")
                        || defaultMessage.equalsIgnoreCase("must not be empty")
                        || defaultMessage.equalsIgnoreCase("must not be null"));

        if ("NotEmpty".equals(annotationType) || "NotNull".equals(annotationType)) {
            if (isDefaultTemplate) {
                return label + " không được để trống";
            }
        }

        // Với các annotation khác (Size, ValueIn...) giữ nguyên message đã khai báo
        return defaultMessage;
    }

    private String getFieldLabel(Class<?> targetClass, String fieldName) {
        if (targetClass == null) {
            return fieldName;
        }
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            Schema schema = field.getAnnotation(Schema.class);
            if (schema != null && !schema.title().isEmpty()) {
                return schema.title();
            }
        } catch (NoSuchFieldException e) {
            // field không tìm thấy, fallback dùng tên field
        }
        return fieldName;
    }
}
