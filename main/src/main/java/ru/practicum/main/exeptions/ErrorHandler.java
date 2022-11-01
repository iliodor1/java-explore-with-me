package ru.practicum.main.exeptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice("ru.practicum.main")
public class ErrorHandler {
    public final static String SERVER_ERROR_REASON = "Error occurred.";

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException e) {
        return new ErrorResponse(e, BAD_REQUEST, BadRequestException.MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundRequestException(NotFoundException e) {
        return new ErrorResponse(e, NOT_FOUND, NotFoundException.MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    public ErrorResponse handleConflictRequestException(ConflictRequestException e) {
        return new ErrorResponse(e, CONFLICT, ConflictRequestException.MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(CONFLICT)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        return new ErrorResponse(e, CONFLICT, ConflictRequestException.MESSAGE, e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException e) {
        return new ErrorResponse(e, BAD_REQUEST, BadRequestException.MESSAGE, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse(e, BAD_REQUEST, BadRequestException.MESSAGE, e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(Throwable e) {
        return new ErrorResponse(e, INTERNAL_SERVER_ERROR, SERVER_ERROR_REASON, e.getMessage());
    }

}
