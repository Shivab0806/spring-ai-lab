package com.spring.ai.exception;

public class TokenLimitExceedException extends RuntimeException{

    public TokenLimitExceedException(String message) {
        super(message);
    }
}
