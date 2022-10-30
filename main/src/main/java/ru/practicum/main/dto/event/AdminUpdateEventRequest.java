package ru.practicum.main.dto.event;

import lombok.Data;
import ru.practicum.main.models.event.Event;

/**
 * A DTO for the {@link Event} entity
 */
@Data
public class AdminUpdateEventRequest {
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
