package com.example.securityjwtpracticespring.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public String handleResponseStatusException(ResponseStatusException ex) {
            return ex.getMessage();
//        HttpStatusCode status = ex.getStatusCode();
//        return new ResponseEntity<>(errorMessage, status);
    }
}
