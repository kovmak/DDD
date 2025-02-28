package com.krnelx.ddd.exceptions;

public class DimensionExceededException extends RuntimeException {

    public DimensionExceededException(String message) {
        super(message);
    }
}