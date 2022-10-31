package ru.practicum.main.controllers.priv.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.dto.event.AdminUpdateEventRequest;
import ru.practicum.main.dto.event.EventFullDto;
import ru.practicum.main.mappers.event.EventMapper;
import ru.practicum.main.models.event.State;
import ru.practicum.main.services.event.EventService;
import ru.practicum.main.exeptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {
    private final EventService service;
    private final EventMapper mapper;

    @GetMapping
    public List<EventFullDto> findEvents(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        List<State> statesEnum = states == null
                ? null : states.stream().map(this::getEnumState).collect(Collectors.toList());

        return mapper.toFullDtos(
                service.findEvents(users, statesEnum, categories, rangeStart, rangeEnd, from, size)
        );
    }

    @PutMapping("{eventId}")
    public EventFullDto updateByAdmin(
            @PathVariable Long eventId, @RequestBody AdminUpdateEventRequest adminUpdateEventRequest
    ) {
        return mapper.toEventFullDto(service.updateByAdmin(eventId, adminUpdateEventRequest));
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publish(@PathVariable Long eventId) {
        return mapper.toEventFullDto(service.publish(eventId));
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto reject(@PathVariable Long eventId) {
        return mapper.toEventFullDto(service.reject(eventId));
    }

    private State getEnumState(String state) {
        State stateEnum;
        try {
            stateEnum = State.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unknown state: " + state);
        }

        return stateEnum;
    }
}
