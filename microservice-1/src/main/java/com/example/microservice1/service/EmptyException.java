package com.example.microservice1.service;

import com.example.microservice1.exception.AppException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class EmptyException extends AppException {
    public EmptyException(Object message) {
        super(HttpStatus.NOT_FOUND, message.toString(), ErrorAttributeOptions.of(MESSAGE));
    }
}
