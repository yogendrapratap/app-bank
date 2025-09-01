package com.app.bank.appbank.validator;

import com.app.bank.appbank.entity.Account;
import com.app.bank.appbank.exception.FundTransferException;
import com.app.bank.appbank.exception.InvalidAccountDetailsException;
import com.app.bank.appbank.exception.StatementGenerationException;
import com.app.bank.appbank.model.AccountDTO;
import com.app.bank.appbank.model.FundTransferDTO;
import com.app.bank.appbank.model.UserFundTransferDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountValidator {

    public void validateAccountAfterCreate(AccountDTO accountDTO) {
        if (accountDTO == null || accountDTO.getId() == null) {
            throw new IllegalArgumentException("Account cannot be null or have a null ID");
        }
    }


    public void validateFundTransferRequest(FundTransferDTO fundTransferDTO) {
        if (fundTransferDTO == null) {
            throw new FundTransferException("Fund transfer request cannot be null");
        }
        if (fundTransferDTO.getFromAccount() == null) {
            throw new InvalidAccountDetailsException("From account cannot be null or empty");
        }
        if (fundTransferDTO.getToAccount() == null) {
            throw new InvalidAccountDetailsException("To account cannot be null or empty");
        }
        if (fundTransferDTO.getAmount() == null
                || fundTransferDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new FundTransferException("Transfer Amount must be greater than zero");
        }
    }

    public void validateUserAccountDetails(Long userId, Account account) {
        if (account == null) {
            throw new FundTransferException("Insufficient funds in account");
        }
        if (account.getUser() == null || !account.getUser().getId().equals(userId)) {
            throw new InvalidAccountDetailsException("From account not found or does not belong to the user");
        }
    }

    public void validateAccount(Long account) {
        if (account == null) {
            throw new InvalidAccountDetailsException("To account not found.");
        }
    }

    public void validateAccountBalance(FundTransferDTO fundTransferDTO, Account fromAccount) {
        if (fromAccount.getBalance().compareTo(fundTransferDTO.getAmount()) < 0) {
            throw new FundTransferException("Insufficient funds in the from account");
        }
    }

    public void validateStatementRequest(Integer fromDate, Integer toDate) {
        if (fromDate == null || toDate == null) {
            throw new StatementGenerationException("From date and to date cannot be null");
        }
    }

    public void validateAmount(BigDecimal amount) {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new FundTransferException("Invalid funds requested for deduction");
        }
    }

    public void validateUserAccountBalance(UserFundTransferDTO userFundTransferDTO, Account fromAccount) {
        if (fromAccount.getBalance().compareTo(userFundTransferDTO.getAmount()) < 0) {
            throw new FundTransferException("Insufficient funds in the from account");
        }
    }
}
