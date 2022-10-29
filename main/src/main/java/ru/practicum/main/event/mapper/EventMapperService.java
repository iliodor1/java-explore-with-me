package ru.practicum.main.event.mapper;

import ru.practicum.main.event.dto.EventFullDto;
import ru.practicum.main.event.dto.EventShortDto;
import ru.practicum.main.event.model.Event;

public interface EventMapperService {
    Event findById(Long id);

    Long getConfirmedRequests(Event event);

    EventFullDto getEventFullViews(EventFullDto event);

    EventShortDto getEventShortViews(EventShortDto event);


}
