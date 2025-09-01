package com.app.bank.appbank.repository;

import com.app.bank.appbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromAccountAndDateBetween(Long fromAccount, LocalDate start, LocalDate end);

    List<Transaction> findByFromAccountOrToAccountAndDateBetween(Long fromAccountNumber,Long toAccountNumber, LocalDate start, LocalDate end);
}
