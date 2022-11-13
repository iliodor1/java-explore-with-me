package ru.practicum.main.dto.event;

import lombok.Data;
import ru.practicum.main.dto.user.UserShortDto;
import ru.practicum.main.dto.category.CategoryDto;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link Event} entity
 */
@Data
public class EventFullDto {
    /**
     * The event identifier.
     */
    private final Long id;

    /**
     *Short description of the event.
     */
    @NotBlank
    private final String annotation;
    /**
     * The {@link CategoryDto} DTO.
     */
    private final CategoryDto category;
    private final Long confirmedRequests;
    /**
     *Date and time the event was created.
     */
    private final String createdOn;
    /**
     * Full description of the event.
     */
    private final String description;
    /**
     * Date and time of the event.
     */
    @NotBlank
    private final String eventDate;
    /**
     * The {@linc UserShortDto}.
     */
    @NotNull
    private final UserShortDto initiator;
    /**
     * The event location {@link Location}.
     */
    @NotNull
    private final Location location;
    /**
     * Participation of the event is paid (true) or free (false)
     */
    @NotNull
    private final boolean paid;
    /**
     * Limit on the number of participants. Value 0 - means no limit.
     */
    private final int participantLimit;
    private final String publishedOn;
    /**
     *Preliminary moderation of applications.
     */
    private final boolean requestModeration;
    private final State state;
    /**
     *The event title.
     */
    @NotNull
    private final String title;
    /**
     *Total event views
     */
    private Long views;

}
