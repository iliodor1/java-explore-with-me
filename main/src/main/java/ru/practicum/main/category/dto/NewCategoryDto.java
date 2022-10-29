package ru.practicum.main.category.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link ru.practicum.main.category.model.Category} entity
 */
@Data
public class NewCategoryDto implements Serializable {

    @NotBlank(message = "The name must not be empty.")
    private final String name;

    @JsonCreator
    public NewCategoryDto(@JsonProperty("name") String name) {
        this.name = name;
    }

}
