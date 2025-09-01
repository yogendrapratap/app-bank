package com.app.bank.appbank.entity;

import com.app.bank.appbank.helper.TransactionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromAccount;

    private Long toAccount;

    private BigDecimal amount;

    private LocalDate date;

    private String transactionType; // "CREDIT" or "DEBIT"

    private String transactionId;

    public Long getId() {
        return id;
    }

    public Transaction setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public Transaction setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
        return this;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public Transaction setToAccount(Long toAccount) {
        this.toAccount = toAccount;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transaction setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Transaction setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }
}

