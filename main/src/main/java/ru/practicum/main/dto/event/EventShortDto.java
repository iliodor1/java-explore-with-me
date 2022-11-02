package ru.practicum.main.dto.event;

import lombok.Data;
import ru.practicum.main.dto.user.UserDto;
import ru.practicum.main.dto.category.CategoryDto;
import ru.practicum.main.models.event.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Event} entity
 */
@Data
public class EventShortDto implements Serializable {
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
    @NotNull
    /**
     * Date and time of the event.
     */
    private final String eventDate;
    /**
     * The {@linc UserDto}.
     */
    private final UserDto initiator;
    /**
     * Participation of the event is paid (true) or free (false)
     */
    @NotNull
    private final Boolean paid;
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
