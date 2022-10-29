package ru.practicum.main.compilation.service;

import ru.practicum.main.compilation.model.Compilation;

import java.util.List;

public interface CompilationService {
    Compilation create(Compilation compilation);

    void delete(Long id);

    void deleteEvent(Long compId, Long eventId);

    void addEvent(Long compId, Long eventId);

    void deletePinned(Long compId);

    void pin(Long compId);

    Compilation get(Long id);

    List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size);

}
