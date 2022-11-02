package ru.practicum.main.exeptions;

public class ConflictRequestException extends RuntimeException {
    public static final String MESSAGE = "Integrity constraint has been violated.";

    public ConflictRequestException(String message) {
        super(message);
    }

    public ConflictRequestException() {
        super(MESSAGE);
    }

}
