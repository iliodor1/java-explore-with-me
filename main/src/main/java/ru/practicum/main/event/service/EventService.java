package ru.practicum.main.event.service;

import ru.practicum.main.event.dto.AdminUpdateEventRequest;
import ru.practicum.main.event.dto.StatDto;
import ru.practicum.main.event.dto.UpdateEventRequest;
import ru.practicum.main.event.model.Event;
import ru.practicum.main.event.model.Sort;
import ru.practicum.main.event.model.State;

import java.util.List;

public interface EventService {

    Event update(UpdateEventRequest updateEvent, Long userId);

    Event getPublishedEvent(Long id, StatDto statDto);

    Event create(Event event, Long userId);

    List<Event> getEventsByUserId(Long userId, Integer from, Integer size);

    Event publish(Long eventId);

    Event reject(Long eventId);

    Event updateByAdmin(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest);

    List<Event> findEvents(
            List<Long> users,
            List<State> states,
            List<Long> categories,
            String rangeStart,
            String rangeEnd,
            Integer from,
            Integer size
    );

    Event getByUserIdAndEventId(Long userId, Long eventId);

    Event cancel(Long userId, Long eventId);

    Event getEvent(Long eventId);

    List<Event> getEvents(
            String text,
            List<Long> categories,
            Boolean paid,
            String rangeStart,
            String rangeEnd,
            Boolean onlyAvailable,
            Sort sortEnum,
            Integer from,
            Integer size
    );

}
