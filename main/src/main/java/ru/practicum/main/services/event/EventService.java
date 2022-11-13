package ru.practicum.main.services.event;

import ru.practicum.main.dto.event.AdminUpdateEventRequest;
import ru.practicum.main.dto.stat.StatDto;
import ru.practicum.main.dto.event.UpdateEventRequest;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.Sort;
import ru.practicum.main.models.event.State;

import java.util.List;

public interface EventService {

    /**
     * Updates Event and return the updated Event.
     * @param updateEvent object updated object.
     * @param userId UpdateEventRequest identifier.
     * @return updated Event.
     */
    Event update(UpdateEventRequest updateEvent, Long userId);

    /**
     * Getting Event with 'published' state.
     * @param id Event identifier.
     * @param statDto Object sent to stats module to account for event views.
     * @return Published Event with views.
     */
    Event getPublishedEvent(Long id, StatDto statDto);

    /**
     * Adds a new Event.
     * @param event new Event.
     * @param userId event initiator identifier.
     * @return Event object that was created.
     */
    Event create(Event event, Long userId);

    /**
     * Getting list of Events by initiator id.
     * @param userId event initiator identifier.
     * @param from integer value of the initial object on the page.
     * @param size integer number of objects on page.
     * @return list of initiator Events
     */
    List<Event> getEventsByUserId(Long userId, Integer from, Integer size);

    /**
     * Setting publish state for Event by event id.
     * @param eventId Event identifier.
     * @return Event with publish state.
     */
    Event publish(Long eventId);

    /**
     * Setting reject state for Event by event id.
     * @param eventId Event identifier.
     * @return Event with reject state.
     */
    Event reject(Long eventId);

    /**
     * Updates Event and return the updated Event.
     * @param eventId Event identifier.
     * @param adminUpdateEventRequest updated object.
     * @return updated Event
     */
    Event updateByAdmin(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest);

    /**
     * Getting list of events by params.
     * @param users list of initiators identifiers.
     * @param states list of events states.
     * @param categories list of categories id.
     * @param rangeStart date and time no earlier than which the event should occur.
     * @param rangeEnd date and time no later than which the event should occur.
     * @param from integer value of the initial object on the page.
     * @param size integer number of objects on page.
     * @return list of Events.
     */
    List<Event> findEvents(
            List<Long> users,
            List<State> states,
            List<Long> categories,
            String rangeStart,
            String rangeEnd,
            Integer from,
            Integer size
    );

    /**
     * Getting Event by User id and Event id.
     * @param userId current user id.
     * @param eventId event id.
     * @return Event object.
     */
    Event getByUserIdAndEventId(Long userId, Long eventId);

    /**
     * Cancellation of an event.
     * @param userId current user id.
     * @param eventId event id.
     * @return Event object with canceled state.
     * @impl Event can be canceled with pending state.
     */
    Event cancel(Long userId, Long eventId);

    /**
     * Getting event by event id.
     * @param eventId event id.
     * @return Event object by event id.
     */
    Event getEvent(Long eventId);

    /**
     * Getting list of events by params.
     * @param text to search in event annotation or event description.
     * @param categories list of categories id.
     * @param paid search only paid/free events.
     * @param rangeStart date and time no earlier than which the event should occur.
     * @param rangeEnd date and time no later than which the event should occur.
     * @param onlyAvailable only events that haven't reached their membership request limit.
     * @param sortEnum sort ({@link Sort}) by event date 'EVENT_DATE' or by number of views 'VIEWS'.
     * @param from integer value of the initial object on the page.
     * @param size integer number of objects on page.
     * @return list of Events.
     */
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
