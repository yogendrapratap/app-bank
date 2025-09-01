package com.app.bank.appbank.helper;

public enum TransactionType {

    DEBIT("DEBIT"),
    CREDIT("CREDIT");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }
}
