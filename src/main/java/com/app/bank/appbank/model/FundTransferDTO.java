package com.app.bank.appbank.model;

import java.math.BigDecimal;

public class FundTransferDTO {

    private Long fromAccount;
    private Long toAccount;
    private BigDecimal amount;

    public Long getFromAccount() {
        return fromAccount;
    }

    public FundTransferDTO setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
        return this;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public FundTransferDTO setToAccount(Long toAccount) {
        this.toAccount = toAccount;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FundTransferDTO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
