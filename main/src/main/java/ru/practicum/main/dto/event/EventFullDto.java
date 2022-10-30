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
    private final Long id;
    @NotBlank
    private final String annotation;
    private final CategoryDto category;
    private final Long confirmedRequests;
    private final String createdOn;
    private final String description;
    @NotBlank
    private final String eventDate;
    @NotNull
    private final UserShortDto initiator;
    @NotNull
    private final Location location;
    @NotNull
    private final boolean paid;
    private final int participantLimit;
    private final String publishedOn;
    private final boolean requestModeration;
    private final State state;
    @NotNull
    private final String title;
    private Long views;

}
