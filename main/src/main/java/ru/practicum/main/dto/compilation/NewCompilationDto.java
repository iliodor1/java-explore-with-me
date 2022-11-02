package ru.practicum.main.dto.compilation;

import lombok.Data;
import ru.practicum.main.models.compilation.Compilation;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Compilation} entity
 */
@Data
public class NewCompilationDto implements Serializable {
    /**
     *The compilation pinned to the main page.
     */
    private final Boolean pinned;
    /**
     * The compilation title.
     */
    @NotBlank
    private final String title;
    /**
     * List of events identifiers included in the compilation.
     */
    private final List<Long> events;

}
