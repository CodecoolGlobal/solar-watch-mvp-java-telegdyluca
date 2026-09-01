package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.exception.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.security.core.AuthenticationException;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String cityNotFoundExceptionHandler(CityNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authenticationExceptionHandler(AuthenticationException e) {
        return e.getMessage();
    }
}
