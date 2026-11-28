package com.spring.ai.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TokenLimitExceedException.class)
    public ResponseEntity<ErrorResponse> handleTokenLimitExceedException(TokenLimitExceedException ex) {
        ErrorResponse errorResponse = new ErrorResponse("TOKEN_LIMIT_EXCEEDED", ex.getMessage());
        return ResponseEntity.status(429).body(errorResponse);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("ORDER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

}
