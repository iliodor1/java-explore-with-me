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
    /**
     * The event identifier.
     */
    @NotNull(message = "The eventId should not be null.")
    private final Long eventId;

    /**
     *Short description of the event.
     */
    @Size(min = 20, max = 2000)
    private final String annotation;
    /**
     * The category id.
     */
    private final Long category;
    /**
     * Full description of the event.
     */
    @Size(min = 20, max = 7000)
    private final String description;

    /**
     * Date and time of the event.
     */
    private final String eventDate;
    /**
     * Participation of the event is paid (true) or free (false)
     */
    private final Boolean paid;

    /**
     * Limit on the number of participants. Value 0 - means no limit.
     */
    private final Integer participantLimit;

    /**
     *The event title.
     */
    @Size(min = 3, max = 120)
    private final String title;

}
