package ru.practicum.main.exeptions;

public class BadRequestException extends RuntimeException {
    public static final String MESSAGE = "The request was made with an error.";

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super(MESSAGE);
    }

}
