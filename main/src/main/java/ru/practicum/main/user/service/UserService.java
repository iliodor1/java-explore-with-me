package ru.practicum.main.user.service;

import ru.practicum.main.user.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(Long id);

    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    User getUser(Long id);

}
