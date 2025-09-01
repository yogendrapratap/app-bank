package com.app.bank.appbank.model;


public class RegistrationRequestDTO {

    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public RegistrationRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationRequestDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RegistrationRequestDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegistrationRequestDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}

