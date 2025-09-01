package com.app.bank.appbank.model;

import java.util.ArrayList;
import java.util.List;

public class StatementDTO {

    private Long accountNumber;
    private int month;
    private int year;
    private List<TransactionDTO> credits = new ArrayList<>();
    private List<TransactionDTO> debits = new ArrayList<>();

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;

    }

    public List<TransactionDTO> getCredits() {
        return credits;
    }

    public void setCredits(List<TransactionDTO> credits) {
        this.credits = credits;
    }

    public List<TransactionDTO> getDebits() {
        return debits;
    }

    public void setDebits(List<TransactionDTO> debits) {
        this.debits = debits;
    }
}
