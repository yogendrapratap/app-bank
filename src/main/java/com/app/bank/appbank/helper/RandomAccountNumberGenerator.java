package com.app.bank.appbank.helper;

import java.util.Random;

public class RandomAccountNumberGenerator {

    public static Long generateAccountNumber() {
        Random random = new Random();
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;

        Long accountNumber = min + (long)(random.nextDouble() * (max - min + 1));
        System.out.println("Generated 10 digit Account Number: " + accountNumber);

        return accountNumber;
    }
}
