package ru.practicum.main.event.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.event.mapper.EventMapper;
import ru.practicum.main.event.service.EventService;
import ru.practicum.main.event.dto.EventFullDto;
import ru.practicum.main.event.dto.EventShortDto;
import ru.practicum.main.event.dto.NewEventDto;
import ru.practicum.main.event.dto.UpdateEventRequest;
import ru.practicum.main.event.model.Event;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Validated
public class PrivateEventController {
    private final EventService service;
    private final EventMapper mapper;

    @GetMapping
    public List<EventShortDto> getEventsByUserId(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return mapper.toShortDtos(service.getEventsByUserId(userId, from, size));
    }

    @PatchMapping
    public EventFullDto update(
            @RequestBody UpdateEventRequest updateEventRequest,
            @PathVariable Long userId
    ) {

        return mapper.toEventFullDto(service.update(updateEventRequest, userId));
    }

    @PostMapping
    public EventFullDto create(@Valid @RequestBody NewEventDto eventDto, @PathVariable Long userId) {
        Event event = mapper.toEvent(eventDto);

        return mapper.toEventFullDto(service.create(event, userId));
    }

    @GetMapping("/{eventId}")
    public EventFullDto getByUserIdAndEventId(@PathVariable Long userId,
                                              @PathVariable Long eventId) {
        return mapper.toEventFullDto(service.getByUserIdAndEventId(userId, eventId));
    }

    @PatchMapping("/{eventId}")
    public EventFullDto cancel(@PathVariable Long userId, @PathVariable Long eventId) {
        return mapper.toEventFullDto(service.cancel(userId, eventId));
    }

}
