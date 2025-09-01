package com.app.bank.appbank.model;

import com.app.bank.appbank.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public class AccountDTO {

private Long id;
private Long accountNumber;
private String userName;
private BigDecimal balance;

    public String getUserName() {
        return userName;
    }

    public AccountDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AccountDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public AccountDTO setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountDTO setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}
