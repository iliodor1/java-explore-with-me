package ru.practicum.main.services.event;

import ru.practicum.main.dto.event.AdminUpdateEventRequest;
import ru.practicum.main.dto.stat.StatDto;
import ru.practicum.main.dto.event.UpdateEventRequest;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.Sort;
import ru.practicum.main.models.event.State;

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
