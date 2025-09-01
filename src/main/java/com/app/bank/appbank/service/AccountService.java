package com.app.bank.appbank.service;

import com.app.bank.appbank.entity.Account;
import com.app.bank.appbank.entity.User;
import com.app.bank.appbank.exception.FundTransferException;
import com.app.bank.appbank.helper.RandomAccountNumberGenerator;
import com.app.bank.appbank.helper.TransactionType;
import com.app.bank.appbank.model.*;
import com.app.bank.appbank.repository.AccountRepository;
import com.app.bank.appbank.validator.AccountValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TranscationService transcationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountValidator accountValidator;

    public AccountDTO createAccountForUser(User user, Long accountNumber) {

        if (accountNumber == null || accountNumber <= 0) {
            accountNumber  = generateUniqueAccountNumber();
        }

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setUser(user);
        account.setBalance(new BigDecimal(10000L)); // Initial balance set to "10000"

        account = accountRepository.save(account);

        return modelMapper.map(account, AccountDTO.class);
    }

    public Long generateUniqueAccountNumber() {
        Long accountNumber;
        do {
            accountNumber = RandomAccountNumberGenerator.generateAccountNumber();
        } while (existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    private boolean existsByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).isPresent();
    }


    public FundTransferResponseDTO transferFunds(FundTransferDTO fundTransferDTO, Long userId) {

        Account fromAccount = accountRepository.findByAccountNumberAndUserId(fundTransferDTO.getFromAccount(), userId);
        accountValidator.validateUserAccountDetails(userId, fromAccount);

        Account toAccount = accountRepository.findByAccountNumber(fundTransferDTO.getToAccount()).get();

        accountValidator.validateAccount(toAccount.getAccountNumber());
        accountValidator.validateAccountBalance(fundTransferDTO, fromAccount);

        fromAccount.setBalance(fromAccount.getBalance().subtract(fundTransferDTO.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(fundTransferDTO.getAmount()));


        TransactionDTO transactionDTO = getTransactionDTO(fundTransferDTO, fromAccount, toAccount);

        accountRepository.save(fromAccount);
        transcationService.saveTransaction(transactionDTO);

        transactionDTO.setFromAccount(toAccount.getAccountNumber());
        transactionDTO.setToAccount(fromAccount.getAccountNumber());
        transactionDTO.setTransactionType(TransactionType.CREDIT.name());
        accountRepository.save(toAccount);
        transcationService.saveTransaction(transactionDTO);

        return getFundTransferResponseDTO(fundTransferDTO, fromAccount, toAccount, transactionDTO);
    }

    private static FundTransferResponseDTO getFundTransferResponseDTO(FundTransferDTO fundTransferDTO, Account fromAccount, Account toAccount, TransactionDTO transactionDTO) {
        return getFundTransferResponseDTO(fromAccount, toAccount.getAccountNumber(), fundTransferDTO.getAmount(), transactionDTO);
    }

    private static FundTransferResponseDTO getUserFundTransferResponseDTO(UserFundTransferDTO userFundTransferDTO, Account fromAccount, Long toAccount, TransactionDTO transactionDTO) {
        return getFundTransferResponseDTO(fromAccount, toAccount, userFundTransferDTO.getAmount(), transactionDTO);
    }

    private static FundTransferResponseDTO getFundTransferResponseDTO(Account fromAccount, Long toAccount, BigDecimal userFundTransferDTO, TransactionDTO transactionDTO) {
        FundTransferResponseDTO fundTransferResponse = new FundTransferResponseDTO();
        fundTransferResponse.setFromAccount(fromAccount.getAccountNumber());
        fundTransferResponse.setToAccount(toAccount);
        fundTransferResponse.setAmount(userFundTransferDTO);
        fundTransferResponse.setTransactionId(transactionDTO.getTransactionId());
        fundTransferResponse.setTransactionTimes(transactionDTO.getDate().toString());
        return fundTransferResponse;
    }

    private static TransactionDTO getUserTransactionDTO(UserFundTransferDTO fundTransferDTO, Account fromAccount, Long toAccount) {
        return getTransactionDTO(fromAccount, toAccount, fundTransferDTO.getAmount());
    }

    private static TransactionDTO getTransactionDTO(FundTransferDTO fundTransferDTO, Account fromAccount, Account toAccount) {
        return getTransactionDTO(fromAccount, toAccount.getAccountNumber(), fundTransferDTO.getAmount());
    }

    private static TransactionDTO getTransactionDTO(Account fromAccount, Long toAccount, BigDecimal fundTransferDTO) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFromAccount(fromAccount.getAccountNumber());
        transactionDTO.setToAccount(toAccount);
        transactionDTO.setAmount(fundTransferDTO);
        transactionDTO.setDate(LocalDate.now());
        transactionDTO.setTransactionType(TransactionType.DEBIT.name());
        transactionDTO.setTransactionId(UUID.randomUUID().toString());
        return transactionDTO;
    }


    public List<StatementDTO> generateStatement(Integer month, Integer year, Long userId) {

        List<Account> statementAccounts = accountRepository.findByUserId(userId);

        List<StatementDTO> existingStatements = new ArrayList<>();
        StatementDTO statementDTO = new StatementDTO();
        statementAccounts
                .forEach(account -> {
                    List<TransactionDTO> transactions = transcationService.getTransactionsByAccountAndDateRange(
                            account.getAccountNumber(), month, year);

                   transactions.forEach(transaction -> {
                       TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
                        statementDTO.setAccountNumber(account.getAccountNumber());
                        if( transaction.getTransactionType().equalsIgnoreCase(TransactionType.DEBIT.name())) {
                            statementDTO.getDebits().add(transactionDTO);
                        } else if( transaction.getTransactionType().equalsIgnoreCase(TransactionType.CREDIT.name())) {
                            statementDTO.getCredits().add(transactionDTO);
                        }
                    });
                    existingStatements.add(statementDTO);
                });


        return existingStatements;
    }

    public FundTransferResponseDTO transferFundsForUser(Long userId, UserFundTransferDTO userFundTransferDTO) {

        List<Account> fromAccounts = accountRepository.findByUserId(userId);

        Optional<Account> accountOptional = fromAccounts.stream().
                filter(account -> account.getBalance().compareTo(userFundTransferDTO.getAmount()) >= 0)
                .findFirst();

        Account fromAccount = getAccount(accountOptional);
        accountValidator.validateUserAccountDetails(userId, fromAccount);

        Long toAccount = userFundTransferDTO.getToAccountName();
        accountValidator.validateAccount(toAccount);

        accountValidator.validateUserAccountBalance(userFundTransferDTO, fromAccount);
        fromAccount.setBalance(fromAccount.getBalance().subtract(userFundTransferDTO.getAmount()));

        //toAccount.setBalance(toAccount.getBalance().add(fundTransferDTO.getAmount()));


        TransactionDTO transactionDTO = getUserTransactionDTO(userFundTransferDTO, fromAccount, toAccount);

        accountRepository.save(fromAccount);
        transcationService.saveTransaction(transactionDTO);

       /* transactionDTO.setFromAccount(toAccount.getAccountNumber());
        transactionDTO.setToAccount(fromAccount.getAccountNumber());
        transactionDTO.setTransactionType(TransactionType.CREDIT.name());
        accountRepository.save(toAccount);
        transcationService.saveTransaction(transactionDTO);*/

        return getUserFundTransferResponseDTO(userFundTransferDTO, fromAccount, toAccount, transactionDTO);
    }

    private static Account getAccount(Optional<Account> accountOptional) {
        Account fromAccount = null;
        if (accountOptional.isPresent()) {
            fromAccount = accountOptional.get();
        }else {
            throw new FundTransferException("Insufficient funds in the from account");
        }

        return fromAccount;
    }
}
