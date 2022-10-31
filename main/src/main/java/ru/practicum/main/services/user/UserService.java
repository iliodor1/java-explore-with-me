package ru.practicum.main.services.user;

import ru.practicum.main.models.user.User;

import java.util.List;

public interface UserService {
    /**
     * Adds a new User and returns a User object that was created.
     * @param: User object - new object.
     */
    User create(User user);

    /**
     * Removing a User by user id.
     * @param id User identifier.
     */
    void delete(Long id);

/**
 * Getting a list of Users by params.
 * @param ids - list of user identifiers.
 * @param from integer value of the initial object on the page.
 * @param size integer number of objects on page.
 */
    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    /**
     * Return a User by id.
     * @param id user identifier.
     */
    User getUser(Long id);

}
