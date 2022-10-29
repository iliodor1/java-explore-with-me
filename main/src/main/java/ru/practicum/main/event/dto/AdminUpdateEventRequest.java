package ru.practicum.main.event.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.practicum.main.event.model.Event} entity
 */
@Data
public class AdminUpdateEventRequest implements Serializable {
    private final String annotation;
    private final Long category;
    private final String description;
    private final String eventDate;
    private final Location location;
    private final Boolean paid;
    private final Integer participantLimit;
    private final Boolean requestModeration;
    private final String title;

}
