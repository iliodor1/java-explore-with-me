package ru.practicum.main.user.dto;

import lombok.Data;
import ru.practicum.main.user.model.User;

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
