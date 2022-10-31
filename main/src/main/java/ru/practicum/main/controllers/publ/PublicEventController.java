package ru.practicum.main.controllers.publ;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.utils.EventClient;
import ru.practicum.main.dto.event.EventFullDto;
import ru.practicum.main.dto.event.EventShortDto;
import ru.practicum.main.dto.stat.StatDto;
import ru.practicum.main.mappers.event.EventMapper;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.Sort;
import ru.practicum.main.services.event.EventService;
import ru.practicum.main.exeptions.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class PublicEventController {

    private final EventService service;
    private final EventClient client;
    private final EventMapper mapper;

    @GetMapping
    public List<EventShortDto> getEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(required = false, defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            HttpServletRequest request) {
        Sort sortEnum = sort == null ? null : getEnumSort(sort);

        List<Event> events = service.getEvents(
                text,
                categories,
                paid,
                rangeStart,
                rangeEnd,
                onlyAvailable,
                sortEnum,
                from,
                size
        );

        client.postStat(getStatDto(request));
        if (sortEnum == Sort.EVENT_DATE) {
            return mapper.toShortWithViewsDtos(mapper.toShortDtos(events));
        } else {
            return mapper.toShortWithViewsDtos(mapper.toShortDtos(events))
                         .stream()
                         .sorted(Comparator.comparing(EventShortDto::getViews))
                         .collect(Collectors.toList());
        }
    }

    @GetMapping("{id}")
    public EventFullDto getPublishedEvent(@PathVariable Long id, HttpServletRequest request) {
        Event event = service.getPublishedEvent(id, getStatDto(request));
        client.postStat(getStatDto(request));
        EventFullDto eventFullDto = mapper.toEventFullDto(event);

        return mapper.toEventWithViewsDto(eventFullDto);
    }

    private StatDto getStatDto(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now()
                                        .format(formatter);

        return StatDto.builder()
                      .app("ewm-main-service")
                      .uri(request.getRequestURI())
                      .ip(request.getRemoteAddr())
                      .timestamp(timestamp)
                      .build();
    }

    private Sort getEnumSort(String sort) {
        Sort sortEnum;
        try {
            sortEnum = Sort.valueOf(sort.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unknown state: " + sort);
        }

        return sortEnum;
    }

}
