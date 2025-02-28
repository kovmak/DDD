package com.krnelx.ddd.exceptions;

public class InvalidStockOperationException extends RuntimeException {

    public InvalidStockOperationException(String message) {
        super(message);
    }
}
