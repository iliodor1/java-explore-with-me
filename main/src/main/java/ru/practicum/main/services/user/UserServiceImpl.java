package ru.practicum.main.services.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main.exceptions.NotFoundException;
import ru.practicum.main.models.user.User;
import ru.practicum.main.repositories.user.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User create(@NonNull User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getUser(id));
    }

    @Override
    public List<User> getUsers(List<Long> ids, Integer from, Integer size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);

        return repository.findUsers(ids, pageable);
    }

    @Override
    public User getUser(Long id) {
        return repository.findById(id)
                              .orElseThrow(() -> {
                                  log.error("The user not exist with id: " + id);
                                  throw new NotFoundException("The user not exist with id: " + id);
                              });
    }

}
