package com.example.microservice1.controller;

import com.example.microservice1.service.EmptyException;
import com.example.microservice1.service.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorAttributes errorAttributes;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleConflict(WebRequest request, NotFoundException e) {
        return createResponseEntity(request, e.getOptions(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleBlankException(WebRequest request, EmptyException e) {
        return createResponseEntity(request, e.getOptions(), e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull WebRequest request) {
        return createResponseEntity(request, ErrorAttributeOptions.defaults(), "Значения полей не должны быть пустыми", HttpStatus.BAD_REQUEST);
    }


    @SuppressWarnings("unchecked")
    protected <T> ResponseEntity<T> createResponseEntity(WebRequest request, ErrorAttributeOptions options, String msg, HttpStatus status) {
        Map<String, Object> body = errorAttributes.getErrorAttributes(request, options);
        body.put("message", msg);
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        return (ResponseEntity<T>) ResponseEntity.status(status).body(body);
    }
}
