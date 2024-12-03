package com.kapasiya.em.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Map<String, String>> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            Object rejectedValue = ((FieldError) error).getRejectedValue();

            Map<String, String> errorDetails = new HashMap<>();
            if (rejectedValue == null || rejectedValue == "") {
                errorDetails.put("message", "The value cannot be empty. Instead, use 'blank'.");
                errorDetails.put("rejectedValue", String.valueOf(rejectedValue));
                fieldErrors.put(fieldName, errorDetails);
            } else {
                errorDetails.put("message", errorMessage);
                errorDetails.put("rejectedValue", String.valueOf(rejectedValue));

                fieldErrors.put(fieldName, errorDetails);
            }
        });

        response.put("timestamp", System.currentTimeMillis());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("fieldErrors", fieldErrors);
        response.put("message", "Invalid input data");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
