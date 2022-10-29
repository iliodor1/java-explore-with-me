package ru.practicum.main.compilation.dto;

import lombok.Data;
import ru.practicum.main.compilation.model.Compilation;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Compilation} entity
 */
@Data
public class NewCompilationDto implements Serializable {
    private final Boolean pinned;
    @NotBlank
    private final String title;
    private final List<Long> events;

}
