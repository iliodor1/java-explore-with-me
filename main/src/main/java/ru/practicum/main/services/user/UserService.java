package ru.practicum.main.services.user;

import ru.practicum.main.models.user.User;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(Long id);

    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    User getUser(Long id);

}
