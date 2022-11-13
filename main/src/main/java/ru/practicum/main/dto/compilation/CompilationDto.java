package ru.practicum.main.dto.compilation;

import lombok.Data;
import ru.practicum.main.dto.event.EventShortDto;
import ru.practicum.main.models.compilation.Compilation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Compilation} entity
 */
@Data
public class CompilationDto implements Serializable {
    /**
     * The compilation identifier.
     */
    @NotNull
    private final Long id;
    /**
     *The compilation pinned to the main page.
     */
    @NotNull
    private final Boolean pinned;
    /**
     * The compilation title.
     */
    @NotBlank
    private final String title;
    /**
     * List of events {@link EventShortDto} included in the compilation.
     */
    private final List<EventShortDto> events;

}
