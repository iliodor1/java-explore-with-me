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
    @NotNull
    private final Long id;
    @NotNull
    private final Boolean pinned;
    @NotBlank
    private final String title;
    private final List<EventShortDto> events;

}
