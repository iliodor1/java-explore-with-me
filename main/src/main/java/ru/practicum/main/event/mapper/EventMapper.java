package ru.practicum.main.event.mapper;

import org.mapstruct.*;
import ru.practicum.main.event.dto.*;
import ru.practicum.main.event.model.Event;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EventMapperService.class})
public interface EventMapper {
    @Mapping(source = "category", target = "category.id")
    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "location.lat", target = "lat")
    @Mapping(source = "location.lon", target = "lon")
    @Mapping(source = "paid", target = "paid", defaultValue = "false")
    @Mapping(source = "requestModeration", target = "requestModeration", defaultValue = "true")
    @Mapping(source = "participantLimit", target = "participantLimit", defaultValue = "0")
    Event toEvent(NewEventDto newEventDto);

    @Mapping(target = "createdOn", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "publishedOn", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "views", ignore = true)
    @Mapping(source = "event.lat", target = "location.lat")
    @Mapping(source = "event.lon", target = "location.lon")
    @Mapping(target = "confirmedRequests", expression = "java(eventMapperService.getConfirmedRequests(event))")
    EventFullDto toEventFullDto(Event event);

    @Mapping(target = "views", expression = "java(eventMapperService.getEventFullViews(event).getViews())")
    EventFullDto toEventWithViewsDto(EventFullDto event);

    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "views", ignore = true)
    @Mapping(target = "confirmedRequests", expression = "java(eventMapperService.getConfirmedRequests(event))")
    EventShortDto toEventShortDto(Event event);

    List<Event> idsToEvents(List<Long> value);

    List<EventFullDto> toFullDtos(List<Event> events);

    List<EventShortDto> toShortDtos(List<Event> events);

    List<EventShortDto> toShortWithViewsDtos(List<EventShortDto> events);

    @Mapping(source = "category", target = "category.id")
    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event updateEvent(UpdateEventRequest updateEventRequest, @MappingTarget Event event);

    @Mapping(source = "category", target = "category.id")
    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "location.lat", target = "lat")
    @Mapping(source = "location.lon", target = "lon")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event updateEvent(AdminUpdateEventRequest updateEventRequest, @MappingTarget Event event);

}
