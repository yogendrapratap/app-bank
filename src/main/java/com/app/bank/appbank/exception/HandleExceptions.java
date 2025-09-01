package com.app.bank.appbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(UserCreationException.class)
    public ErrorMessage handleUserCreationException(UserCreationException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(UserRegistrationRequestException.class)
    public ErrorMessage handleUserCreationException(UserRegistrationRequestException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(FundTransferException.class)
    public ErrorMessage handleFundTransferException(FundTransferException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(InvalidAccountDetailsException.class)
    public ErrorMessage handleInvalidAccountDetailsException(InvalidAccountDetailsException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(StatementGenerationException.class)
    public ErrorMessage handleStatementGenerationException(StatementGenerationException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage handleException(Exception ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }
}
