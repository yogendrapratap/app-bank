package com.app.bank.appbank.model;

import com.app.bank.appbank.helper.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTO  {

    @JsonIgnore
    private Long id;
    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;
    private LocalDate date;
    private String transactionType;
    private String transactionId;

    public Long getId() {
        return id;
    }

    public TransactionDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;

    }

    public Long getToAccount() {
        return toAccount;
    }

    public void setToAccount(Long toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }
    public TransactionDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public TransactionDTO setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionDTO setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }
}
