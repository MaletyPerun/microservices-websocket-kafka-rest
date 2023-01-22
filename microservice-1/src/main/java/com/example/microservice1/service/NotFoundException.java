package com.example.microservice1.service;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class NotFoundException extends AppException {
    public NotFoundException(Object message) {
        super(HttpStatus.NOT_FOUND, message.toString(), ErrorAttributeOptions.of(MESSAGE));
    }
}
