package ru.practicum.main.dto.event;

import lombok.Data;
import ru.practicum.main.models.event.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link Event} entity
 */

@Data
public class NewEventDto {

    /**
     *Short description of the event.
     */
    @Size(min = 20, max = 2000)
    @NotBlank(message = "The annotation should not be empty.")
    private final String annotation;
    /**
     * The category id.
     */
    @NotNull(message = "The category should not be null.")
    private final Long category;
    /**
     * Full description of the event.
     */
    @Size(min = 20, max = 7000)
    @NotBlank(message = "The description should not be empty.")
    private final String description;
    /**
     * Date and time of the event.
     */
    @NotBlank(message = "The eventDate should not be empty.")
    private final String eventDate;
    /**
     * The event location {@link Location}.
     */
    @NotNull(message = "The location should not be null.")
    private final Location location;
    /**
     * Participation of the event is paid (true) or free (false)
     */
    private final Boolean paid;

    /**
     * Limit on the number of participants. Value 0 - means no limit.
     */
    @PositiveOrZero(message = "The participantLimit should not be negative.")
    private final int participantLimit;

    /**
     *Preliminary moderation of applications.
     */
    private final Boolean requestModeration;

    /**
     *The event title.
     */
    @Size(min = 3, max = 120)
    @NotBlank(message = "The title should not be empty.")
    private final String title;

}
