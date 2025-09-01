package com.app.bank.appbank.helper;

public enum StatusType {
    SUCCESS("Success"),
    FAILURE("Failure"),
    INSUFFICIENT_BALANCE("Insufficient Balance"),
    ACCOUNT_NOT_FOUND("Account Not Found"),
    INVALID_AMOUNT("Invalid Amount"),
    UNAUTHORIZED_ACCESS("Unauthorized Access");

    private final String message;

    StatusType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
