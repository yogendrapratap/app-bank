package com.app.bank.appbank.exception;

public class InvalidAccountDetailsException extends RuntimeException {


    public InvalidAccountDetailsException(String message) {
        super(message);
    }
}
