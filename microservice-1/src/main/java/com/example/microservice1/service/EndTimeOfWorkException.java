package com.example.microservice1.service;

import com.example.microservice1.exception.AppException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class EndTimeOfWorkException extends AppException {
    public EndTimeOfWorkException(int seconds) {
        super(HttpStatus.REQUEST_TIMEOUT, String.valueOf(seconds), ErrorAttributeOptions.of(MESSAGE));
    }
}
