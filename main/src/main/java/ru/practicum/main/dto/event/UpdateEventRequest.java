package ru.practicum.main.dto.event;

import lombok.Data;
import ru.practicum.main.models.event.Event;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Event} entity
 */
@Data
public class UpdateEventRequest implements Serializable {
    @NotNull(message = "The eventId should not be null.")
    private final Long eventId;

    @Size(min = 20, max = 2000)
    private final String annotation;

    private final Long category;

    @Size(min = 20, max = 7000)
    private final String description;

    private final String eventDate;

    private final Boolean paid;

    private final Integer participantLimit;

    @Size(min = 3, max = 120)
    private final String title;

}
