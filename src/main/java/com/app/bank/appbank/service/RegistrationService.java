package com.app.bank.appbank.service;

import com.app.bank.appbank.entity.User;
import com.app.bank.appbank.model.AccountDTO;
import com.app.bank.appbank.model.RegistrationRequestDTO;
import com.app.bank.appbank.model.RegistrationResponseDTO;
import com.app.bank.appbank.repository.RegistrationRepository;
import com.app.bank.appbank.validator.AccountValidator;
import com.app.bank.appbank.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AccountValidator accountValidator;

    public RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequest) {
        User user = modelMapper.map(registrationRequest, User.class);
        user = registrationRepository.save(user);

        userValidator.validateAfterCreate(user);

        Long accountNumber = accountService.generateUniqueAccountNumber();
        AccountDTO accountDTO = accountService.createAccountForUser(user, accountNumber);

        accountValidator.validateAccountAfterCreate(accountDTO);

        RegistrationResponseDTO responseDTO = modelMapper.map(user, RegistrationResponseDTO.class);
        responseDTO.setAccountDTO(accountDTO);
        return responseDTO;
    }
}
