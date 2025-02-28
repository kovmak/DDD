package com.krnelx.ddd.exceptions;

public class InvalidAddressException extends RuntimeException {

    public InvalidAddressException(String message) {
        super(message);
    }
}
