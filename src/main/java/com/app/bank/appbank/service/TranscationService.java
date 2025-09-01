package com.app.bank.appbank.service;

import com.app.bank.appbank.entity.Transaction;
import com.app.bank.appbank.model.TransactionDTO;
import com.app.bank.appbank.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TranscationService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transactionRepository.save(transaction);

        return modelMapper.map(transaction, TransactionDTO.class);
    }

    /*public List<TransactionDTO> getTransactionsByAccountAndDateRange(Long accountNumber, String fromDate, String toDate) {

        transactionRepository.findByAccountNumberAndDateRange(accountNumber, fromDate, toDate)
                .orElseThrow(() -> new RuntimeException("No transactions found for the given account and date range"));
    }*/


    public List<TransactionDTO> getTransactionsByAccountAndDateRange(Long accountNumber, Integer month, Integer year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Transaction> translations = transactionRepository.findByFromAccountAndDateBetween(accountNumber, start, end);

        //List<Transaction> translations = transactionRepository.findByFromAccountOrToAccountAndDateBetween(accountNumber, accountNumber, start, end);


        return  translations.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }
}
