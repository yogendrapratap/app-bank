package com.app.bank.appbank.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, unique = true, nullable = false)
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Account setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}

