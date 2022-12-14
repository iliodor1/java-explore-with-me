package ru.practicum.main.controllers.priv.user;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.user.NewUserRequest;
import ru.practicum.main.dto.user.UserDto;
import ru.practicum.main.mappers.user.UserMapper;
import ru.practicum.main.models.user.User;
import ru.practicum.main.services.user.UserService;

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
