package com.example.TeacherAppServer.controller;

import com.example.TeacherAppServer.domain.dto.error.CustomFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CustomFieldError>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<CustomFieldError> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new CustomFieldError(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
