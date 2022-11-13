package ru.practicum.main.mappers.event;

import ru.practicum.main.dto.event.EventFullDto;
import ru.practicum.main.dto.event.EventShortDto;
import ru.practicum.main.models.event.Event;

public interface EventMapperService {
    Event findById(Long id);

    Long getConfirmedRequests(Event event);

    EventFullDto getEventFullViews(EventFullDto event);

    EventShortDto getEventShortViews(EventShortDto event);


}
