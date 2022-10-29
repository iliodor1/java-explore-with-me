package ru.practicum.main.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.user.dto.NewUserRequest;
import ru.practicum.main.user.dto.UserDto;
import ru.practicum.main.user.mapper.UserMapper;
import ru.practicum.main.user.model.User;
import ru.practicum.main.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return mapper.toDtos(service.getUsers(ids, from, size));
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody NewUserRequest newUserRequest) {
        User user = mapper.toUser(newUserRequest);

        return mapper.toUserDto(service.create(user));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(name = "userId") Long id) {
        service.delete(id);
    }

}
