package ru.practicum.main.dto.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.practicum.main.models.category.Category;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
public class NewCategoryDto implements Serializable {
    /**
     * Name of category.
     */
    @NotBlank(message = "The name must not be empty.")
    private final String name;

    @JsonCreator
    public NewCategoryDto(@JsonProperty("name") String name) {
        this.name = name;
    }

}
