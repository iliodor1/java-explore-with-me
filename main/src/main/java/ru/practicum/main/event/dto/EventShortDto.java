package ru.practicum.main.event.dto;

import lombok.Data;
import ru.practicum.main.category.dto.CategoryDto;
import ru.practicum.main.event.model.Event;
import ru.practicum.main.user.dto.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Event} entity
 */
@Data
public class EventShortDto implements Serializable {
    private final Long id;
    @NotBlank
    private final String annotation;
    private final CategoryDto category;
    private final Long confirmedRequests;
    @NotNull
    private final String eventDate;
    private final UserDto initiator;
    @NotNull
    private final Boolean paid;
    @NotNull
    private final String title;
    private Long views;

}
