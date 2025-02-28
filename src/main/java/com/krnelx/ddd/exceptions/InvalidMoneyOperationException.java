package com.krnelx.ddd.exceptions;

public class InvalidMoneyOperationException extends RuntimeException {

    public InvalidMoneyOperationException(String message) {
        super(message);
    }
}
