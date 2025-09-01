package com.app.bank.appbank.validator;

import com.app.bank.appbank.entity.User;
import com.app.bank.appbank.exception.FundTransferException;
import com.app.bank.appbank.exception.UserCreationException;
import com.app.bank.appbank.exception.UserRegistrationRequestException;
import com.app.bank.appbank.model.RegistrationRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validate(Long userId) {
        if (userId == null || userId <= 0 || String.valueOf(userId).isEmpty()) {
            throw new FundTransferException("User ID be a valid id");
        }
    }
    public void validateName(String name) {
        if (name == null) {
            throw new FundTransferException("User name be a valid");
        }
    }

    public void validateAfterCreate(User user) {
        if (user == null || user.getId() == null) {
            throw new UserCreationException("User cannot be null");
        }
    }

    public void validateUserRequest(RegistrationRequestDTO registrationRequest) {
        if (registrationRequest == null || registrationRequest.getName() == null
                || registrationRequest.getEmail() == null
        || registrationRequest.getPhoneNumber() == null){
            throw new UserRegistrationRequestException("Invalid registration request");
        }
    }

    public void validateUserIdAndName(Long userId, String name) {
        validate(userId);
        validateName(name);
    }
}
