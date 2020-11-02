package com.ing.userservice.core.exception;

public class CircuitBreakerException extends RuntimeException {

    public CircuitBreakerException(String message) {
        super(message);
    }
}
