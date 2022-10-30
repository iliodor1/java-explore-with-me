package ru.practicum.main.services.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.main.models.category.Category;
import ru.practicum.main.dto.event.AdminUpdateEventRequest;
import ru.practicum.main.dto.event.UpdateEventRequest;
import ru.practicum.main.mappers.event.EventMapper;
import ru.practicum.main.repositories.event.EventRepository;
import ru.practicum.main.dto.stat.StatDto;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.State;
import ru.practicum.main.exeptions.BadRequestException;
import ru.practicum.main.exeptions.ConflictRequestException;
import ru.practicum.main.exeptions.NotFoundException;
import ru.practicum.main.services.user.UserService;
import ru.practicum.main.services.category.CategoryService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final EventMapper mapper;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            CategoryService categoryService,
                            UserService userService,
                            EventMapper mapper) {
        this.eventRepository = eventRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public List<Event> getEventsByUserId(Long userId, Integer from, Integer size) {
        Pageable pageable = getPageRequest(from, size);

        return eventRepository.findByInitiatorId(userId, pageable);
    }

    @Override
    public Event updateByAdmin(Long eventId, AdminUpdateEventRequest updateEventRequest) {
        Event updateEvent = mapper.updateEvent(updateEventRequest, getEvent(eventId));

        return eventRepository.save(updateEvent);
    }

    @Override
    public List<Event> findEvents(
            List<Long> users,
            List<State> states,
            List<Long> categories,
            String rangeStart,
            String rangeEnd,
            Integer from,
            Integer size
    ) {
        Pageable pageable = getPageRequest(from, size);

        LocalDateTime start = rangeStart == null ? null : getDateTime(rangeStart);
        LocalDateTime end = rangeEnd == null ? null : getDateTime(rangeEnd);

        if (start == null && end == null) {
            return eventRepository.findByParams(users, states, categories, pageable);
        } else if (start == null) {
            return eventRepository.findByParamsWithRangeEnd(users, states, categories, end, pageable);
        } else if (end == null) {
            return eventRepository.findByParamsWithRangeStart(users, states, categories, start, pageable);
        } else {
            return eventRepository.findByParams(users, states, categories, start, end, pageable);
        }
    }

    @Override
    public Event getByUserIdAndEventId(Long userId, Long eventId) {
        return eventRepository.findByInitiator_IdAndId(userId, eventId)
                              .orElseThrow(() -> new NotFoundException(String.format(
                                      "Event with id %d created by user with id %d does not exist",
                                      eventId, userId
                              )));
    }

    @Override
    public Event cancel(Long userId, Long eventId) {
        Event event = getEvent(eventId);

        if (event.getState()
                 .equals(State.PENDING)) {
            event.setState(State.CANCELED);

            return eventRepository.save(event);
        } else {
            log.error("The event with state '{}' cannot be canceled.", event.getState());
            throw new BadRequestException("Only pending or canceled events can be changed");
        }
    }

    @Override
    public Event publish(Long eventId) {
        Event event = getEvent(eventId);

        if (event.getEventDate()
                 .minusHours(1)
                 .isBefore(LocalDateTime.now())) {
            log.error("Event can't publish after '{}' ", event.getEventDate()
                                                              .minusHours(1));
            throw new ConflictRequestException("Event can't publish after " + event.getEventDate());
        }
        if (!(event.getState()
                   .equals(State.PENDING))) {
            log.error("Event can't publish if state not 'PENDING'");
            throw new ConflictRequestException("Event can't publish if state not 'PENDING'");
        }

        event.setState(State.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());

        return eventRepository.save(event);
    }

    @Override
    public Event reject(Long eventId) {
        Event event = getEvent(eventId);
        if (event.getState()
                 .equals(State.PUBLISHED)) {
            log.error("Event can't reject if state 'PUBLISHED'");
            throw new ConflictRequestException("Event can't reject if state 'PUBLISHED'");
        }

        event.setState(State.CANCELED);

        return eventRepository.save(event);
    }

    @Override
    public Event update(UpdateEventRequest updateEventRequest, Long userId) {
        Event event = eventRepository.findById(updateEventRequest.getEventId())
                                     .orElseThrow(() -> {
                                         log.error("The event id '{}' not found", updateEventRequest.getEventId());
                                         throw new NotFoundException("The required object was not found.");
                                     });

        if (!(event.getInitiator()
                   .getId()
                   .equals(userId))) {
            log.error("The event '{}' can only be updated by the initiator.", event.getId());
            throw new BadRequestException("Only the initiator can change events");
        }

        if (event.getState()
                 .equals(State.PUBLISHED)) {
            log.error("The event id '{}' with state '{}' cannot be changed.", event.getId(), event.getState());
            throw new BadRequestException("Only pending or canceled events can be changed.");
        }

        Event updateEvent = mapper.updateEvent(updateEventRequest, event);

        if (updateEvent.getEventDate()
                       .minusHours(2)
                       .isBefore(LocalDateTime.now())) {
            log.error("The event cannot be earlier than two hours from the current.");
            throw new BadRequestException(
                    "The event cannot be earlier than two hours from the current."
            );
        }
        event.setState(State.PENDING);

        return eventRepository.save(updateEvent);
    }

    @Override
    public Event create(Event event, Long userId) {
        if (event.getEventDate()
                 .minusHours(2)
                 .isBefore(LocalDateTime.now())) {
            log.error("The event cannot be earlier than two hours from the current.");
            throw new BadRequestException(
                    "The event cannot be earlier than two hours from the current."
            );
        }

        event.setCreatedOn(LocalDateTime.now());
        event.setState(State.PENDING);

        Category category = categoryService.getCategory(event.getCategory()
                                                             .getId());
        event.setCategory(category);

        event.setInitiator(userService.getUser(userId));

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getEvents(
            String text,
            List<Long> categories,
            Boolean paid,
            String rangeStart,
            String rangeEnd,
            Boolean onlyAvailable,
            ru.practicum.main.models.event.Sort sortEnum,
            Integer from,
            Integer size
    ) {
        Pageable pageable;

        if (sortEnum == ru.practicum.main.models.event.Sort.EVENT_DATE) {
            pageable = PageRequest.of(from / size, size, Sort.by("eventDate"));
        } else {
            pageable = getPageRequest(from, size);
        }

        LocalDateTime start = rangeStart == null ? LocalDateTime.now() : getDateTime(rangeStart);
        LocalDateTime end = rangeEnd == null ? null : getDateTime(rangeEnd);

        if (onlyAvailable && end != null) {
            return eventRepository.findPublishedNotAvailableEventsByParams(
                    text, categories, paid, start, end, pageable
            );
        } else if (!onlyAvailable && end != null) {
            return eventRepository.findPublishedAvailableEventsByParams(
                    text, categories, paid, start, end, pageable
            );
        } else if (onlyAvailable) {
            return eventRepository.findPublishedAvailableEventsByParams(
                    text, categories, paid, start, pageable
            );
        } else {
            return eventRepository.findPublishedAvailableEventsByParams(
                    text, categories, paid, start, pageable
            );
        }

    }

    @Override
    public Event getPublishedEvent(Long id, StatDto statDto) {
        return eventRepository.findByIdAndState(id, State.PUBLISHED)
                              .orElseThrow(() -> new NotFoundException("The required object was not found."));
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                              .orElseThrow(() -> new NotFoundException("The required object was not found."));
    }

    private PageRequest getPageRequest(Integer from, Integer size) {
        return PageRequest.of(from / size, size);
    }

    private LocalDateTime getDateTime(String rangeStart) {
        return rangeStart == null ? null : LocalDateTime.parse(rangeStart, FORMATTER);
    }

}
