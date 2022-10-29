package ru.practicum.main.user.dto;

import lombok.Data;
import ru.practicum.main.user.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class NewUserRequest implements Serializable {
    @NotBlank(message = "The name must not be empty.")
    @Size(max = 255)
    private final String name;

    @NotNull(message = "The email must not be null.")
    @Email(message = "Invalid email address.")
    @Size(max = 255)
    private final String email;

}
