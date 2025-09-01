package com.app.bank.appbank.controller;

import com.app.bank.appbank.model.*;
import com.app.bank.appbank.service.AccountService;
import com.app.bank.appbank.service.RegistrationService;
import com.app.bank.appbank.validator.AccountValidator;
import com.app.bank.appbank.validator.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AccountValidator accountValidator;

    @PostMapping("/registration")
    @Operation(
            summary = "User Registration",
            description = "Endpoint to register a new user with account creation",
            tags = {"User Management"}
    )
    public ResponseEntity<RegistrationResponseDTO> registerUser(@RequestBody RegistrationRequestDTO registrationRequest) {

        userValidator.validateUserRequest(registrationRequest);
        return ResponseEntity.ok(registrationService.registerUser(registrationRequest));
    }

    @PostMapping("/fund-transfer/{userId}")
    @Operation(
            summary = "Fund Transfer",
            description = "Endpoint to transfer funds between accounts",
            tags = {"Account Management"}
    )
    public FundTransferResponseDTO transferFunds(@RequestBody FundTransferDTO fundTransferDTO,
                                                     @PathVariable("userId") Long userId) {
        userValidator.validate(userId);
        accountValidator.validateFundTransferRequest(fundTransferDTO);

        return accountService.transferFunds(fundTransferDTO, userId);
    }

    @PostMapping("/online-amount-transfer/{userId}")
    @Operation(
            summary = "Fund Transfer to ecommerce account",
            description = "Endpoint to transfer funds between accounts",
            tags = {"Account Management"}
    )
    public FundTransferResponseDTO transferFundsForEcommerceAccount(@RequestBody UserFundTransferDTO userFundTransfer,
                                                 @PathVariable("userId") Long userId) {

        userValidator.validateUserIdAndName(userId, userFundTransfer.getUserName());
        accountValidator.validateAmount(userFundTransfer.getAmount());

        return accountService.transferFundsForUser(userId, userFundTransfer);
    }

    @PostMapping("/statement/{userId}")
    @Operation(
            summary = "Generate Account Statement",
            description = "Endpoint to generate account statement for a user",
            tags = {"Account Management"}
    )
    public List<StatementDTO> generateStatement(
            @PathVariable("userId") Long userId,
            @RequestParam("month") Integer month,
            @RequestParam("year") Integer year) {

        userValidator.validate(userId);
        accountValidator.validateStatementRequest(month, year);

        return accountService.generateStatement(month, year, userId);
    }
}
