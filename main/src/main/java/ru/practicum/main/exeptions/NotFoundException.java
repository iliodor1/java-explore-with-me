package ru.practicum.main.exeptions;

public class NotFoundException extends RuntimeException {
    private final static String MESSAGE = "The required object was not found.";

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super(MESSAGE);
    }

}
