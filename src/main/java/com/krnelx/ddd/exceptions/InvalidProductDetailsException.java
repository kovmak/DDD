package com.krnelx.ddd.exceptions;

public class InvalidProductDetailsException extends RuntimeException {

    public InvalidProductDetailsException(String message) {
        super(message);
    }
}
