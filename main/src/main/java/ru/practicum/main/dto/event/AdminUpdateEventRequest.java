package ru.practicum.main.dto.event;

import lombok.Data;
import ru.practicum.main.models.event.Event;

/**
 * A DTO for the {@link Event} entity
 */
@Data
public class AdminUpdateEventRequest {

    /**
     *Short description of the event.
     */
    private final String annotation;
    /**
     * The category id.
     */
    private final Long category;
    /**
     * Full description of the event.
     */
    private final String description;

    /**
     * Date and time of the event.
     */
    private final String eventDate;
    /**
     * The event location {@link Location}.
     */
    private final Location location;
    /**
     * Participation of the event is paid (true) or free (false)
     */
    private final Boolean paid;
    /**
     * Limit on the number of participants. Value 0 - means no limit.
     */
    private final Integer participantLimit;
    /**
     *Preliminary moderation of applications.
     */
    private final Boolean requestModeration;
    /**
     *The event title.
     */
    private final String title;

}
