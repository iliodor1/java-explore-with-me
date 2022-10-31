package ru.practicum.main.exeptions;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ErrorResponse {
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final List<StackTraceElement> errors;
    private final String message;
    private final String reason;
    private final HttpStatus status;
    private final String timestamp;

    public ErrorResponse(Throwable exception, HttpStatus status, String reason, String message) {
        this.errors = List.of(exception.getStackTrace());
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DATE_TIME);
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
