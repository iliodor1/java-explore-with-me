package ru.practicum.main.dto.user;

import lombok.Data;
import ru.practicum.main.models.user.User;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String name;
    private final String email;

}
