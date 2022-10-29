package ru.practicum.main.event.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.practicum.main.event.client.EventClient;
import ru.practicum.main.event.dto.EventFullDto;
import ru.practicum.main.event.dto.EventShortDto;
import ru.practicum.main.event.dto.ViewStat;
import ru.practicum.main.event.model.Event;
import ru.practicum.main.event.repository.EventRepository;
import ru.practicum.main.exeption.NotFoundException;
import ru.practicum.main.request.model.Status;
import ru.practicum.main.request.repository.ParticipationRequestRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventMapperServiceImpl implements EventMapperService {
    private final EventRepository repository;
    private final ParticipationRequestRepository requestRepo;
    private final EventClient client;

    @Override
    public Event findById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new NotFoundException("Event not found with id '' " + id));
    }

    @Override
    public Long getConfirmedRequests(Event event) {
        Status status = Status.CONFIRMED;

        return requestRepo.countEventConfirmedRequests(event.getId(), status);
    }

    @Override
    public EventFullDto getEventFullViews(EventFullDto event) {
        Long views = getViews(event.getId()).getHits();
        event.setViews(views == null ? 0 : views);

        return event;
    }

    @Override
    public EventShortDto getEventShortViews(EventShortDto event) {
        Long views = getViews(event.getId()).getHits();
        event.setViews(views == null ? 0 : views);

        return event;
    }

    private ViewStat getViews(Long id) {
        ObjectMapper om = new ObjectMapper();
        ResponseEntity<Object> response = client.getViews(id);

        Object object = response.getBody();

        return om.convertValue(object, new TypeReference<List<ViewStat>>() {
        }).get(0);
    }

}
