package ru.practicum.main.category.dto;

import lombok.Data;
import ru.practicum.main.category.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
public class CategoryDto implements Serializable {

    @NotNull(message = "The id must not be null.")
    private final Long id;

    @NotBlank(message = "The name must not be empty.")
    @Size(max = 255)
    private final String name;

}
