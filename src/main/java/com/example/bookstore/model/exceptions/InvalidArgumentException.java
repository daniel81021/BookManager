package com.example.bookstore.model.exceptions;

import java.security.InvalidParameterException;

public class InvalidArgumentException extends InvalidParameterException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidArgumentException() {
        super();
    }
}
