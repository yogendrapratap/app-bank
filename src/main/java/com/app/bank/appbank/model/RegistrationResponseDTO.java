package com.app.bank.appbank.model;


public class RegistrationResponseDTO {

    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private AccountDTO accountDTO;


    public String getName() {
        return name;
    }

    public RegistrationResponseDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationResponseDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RegistrationResponseDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegistrationResponseDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public RegistrationResponseDTO setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
        return this;
    }
}

