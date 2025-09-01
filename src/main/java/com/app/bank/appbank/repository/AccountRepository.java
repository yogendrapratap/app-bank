package com.app.bank.appbank.repository;

import com.app.bank.appbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(Long accountNumber);

    Account findByAccountNumberAndUserId(Long fromAccount, Long userId);

    List<Account> findByUserId(Long userId);
}
