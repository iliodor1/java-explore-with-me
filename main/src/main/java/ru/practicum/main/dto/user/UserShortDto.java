package ru.practicum.main.dto.user;

import lombok.Data;
import ru.practicum.main.models.user.User;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserShortDto implements Serializable {
    /**
     * The User identifier.
     */
    private final Long id;
    /**
     * First and last name of the user.
     */
    private final String name;

}
