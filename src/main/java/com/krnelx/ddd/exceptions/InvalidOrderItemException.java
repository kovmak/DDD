package com.krnelx.ddd.exceptions;

public class InvalidOrderItemException extends RuntimeException {

    public InvalidOrderItemException(String message) {
        super(message);
    }
}
