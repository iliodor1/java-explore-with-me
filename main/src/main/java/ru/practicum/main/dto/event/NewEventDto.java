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
    @Size(min = 20, max = 2000)
    @NotBlank(message = "The annotation should not be empty.")
    private final String annotation;

    @NotNull(message = "The category should not be null.")
    private final Long category;

    @Size(min = 20, max = 7000)
    @NotBlank(message = "The description should not be empty.")
    private final String description;

    @NotBlank(message = "The eventDate should not be empty.")
    private final String eventDate;

    @NotNull(message = "The location should not be null.")
    private final Location location;

    private final Boolean paid;

    @PositiveOrZero(message = "The participantLimit should not be negative.")
    private final int participantLimit;

    private final Boolean requestModeration;

    @Size(min = 3, max = 120)
    @NotBlank(message = "The title should not be empty.")
    private final String title;

}
