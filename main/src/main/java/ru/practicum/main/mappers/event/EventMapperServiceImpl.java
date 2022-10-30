package ru.practicum.main.mappers.event;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.practicum.main.client.EventClient;
import ru.practicum.main.dto.event.EventFullDto;
import ru.practicum.main.dto.event.EventShortDto;
import ru.practicum.main.dto.stat.ViewStat;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.repositories.event.EventRepository;
import ru.practicum.main.exeptions.NotFoundException;
import ru.practicum.main.models.request.Status;
import ru.practicum.main.repositories.request.ParticipationRequestRepository;

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
        ViewStat views = getViews(event.getId());
        Long hits = views == null ? 0 : views.getHits();
        event.setViews(hits);

        return event;
    }

    @Override
    public EventShortDto getEventShortViews(EventShortDto event) {
        ViewStat views = getViews(event.getId());
        Long hits = views == null ? 0 : views.getHits();
        event.setViews(hits);

        return event;
    }

    private ViewStat getViews(Long id) {
        ObjectMapper om = new ObjectMapper();
        ResponseEntity<Object> response = client.getViews(id);

        Object object = response.getBody();

        List<ViewStat> viewStats = om.convertValue(object, new TypeReference<List<ViewStat>>() {
        });

        return viewStats.isEmpty() ? null : viewStats.get(0);
    }

}
