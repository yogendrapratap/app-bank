package com.app.bank.appbank.model;

public class StatementRequestDTO {
    private String accountNumber;
    private int month;
    private int year;

    public String getAccountNumber() {
        return accountNumber;
    }

    public StatementRequestDTO setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public StatementRequestDTO setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getYear() {
        return year;
    }

    public StatementRequestDTO setYear(int year) {
        this.year = year;
        return this;
    }
}
