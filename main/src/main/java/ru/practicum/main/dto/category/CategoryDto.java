package ru.practicum.main.dto.category;

import lombok.Data;
import ru.practicum.main.models.category.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
public class CategoryDto implements Serializable {
    /**
     * The category identifier.
     */
    @NotNull(message = "The id must not be null.")
    private final Long id;
    /**
     * Name of category.
     */
    @NotBlank(message = "The name must not be empty.")
    @Size(max = 255)
    private final String name;

}
