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
    private final Boolean pinned;
    @NotBlank
    private final String title;
    private final List<Long> events;

}
