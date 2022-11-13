package ru.practicum.main.exceptions;

public class NotFoundException extends RuntimeException {
    public static final String MESSAGE = "The required object was not found.";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super(MESSAGE);
    }

}
