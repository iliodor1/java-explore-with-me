package ru.practicum.main.services.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.main.models.compilation.Compilation;
import ru.practicum.main.repositories.compilation.CompilationRepository;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.exeptions.NotFoundException;
import ru.practicum.main.services.event.EventService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;
    private final EventService eventService;

    @Override
    public Compilation create(Compilation compilation) {
        return repository.save(compilation);
    }

    @Override
    public void delete(Long id) {
        repository.delete(get(id));
    }

    @Override
    public void deleteEvent(Long compId, Long eventId) {
        Compilation compilation = get(compId);
        List<Event> events = compilation.getEvents();

        Event event = eventService.getEvent(eventId);
        events.remove(event);

        compilation.setEvents(events);

        repository.save(compilation);
    }

    @Override
    public void addEvent(Long compId, Long eventId) {
        Compilation compilation = get(compId);
        List<Event> events = compilation.getEvents();

        Event event = eventService.getEvent(eventId);
        events.add(event);

        compilation.setEvents(events);
        repository.save(compilation);
    }

    @Override
    public void deletePinned(Long compId) {
        Compilation compilation = get(compId);
        compilation.setPinned(false);
        repository.save(compilation);
    }

    @Override
    public void pin(Long compId) {
        Compilation compilation = get(compId);
        compilation.setPinned(true);
        repository.save(compilation);
    }

    @Override
    public Compilation get(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> {
                             log.error("Compilation with id={} was not found.", id);
                             throw new NotFoundException(
                                     String.format("Compilation with id=%d was not found.", id)
                             );
                         });
    }

    @Override
    public List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);

        return repository.findByParams(pinned, pageable);
    }

}
