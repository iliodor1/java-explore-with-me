package ru.practicum.main.services.compilation;

import ru.practicum.main.models.compilation.Compilation;

import java.util.List;

public interface CompilationService {

    /**
     * Adds a new Compilation.
     * @param: Compilation object - new object.
     * @return Compilation object that was created.
     */
    Compilation create(Compilation compilation);

    /**
     * Removing a Compilation by compilation id.
     * @param id Compilation identifier.
     */
    void delete(Long id);

    /**
     * Deletion of Event from Compilation by an administrator by the event id.
     * @param compId Compilation identifier.
     * @param eventId Event identifier.
     */
    void deleteEvent(Long compId, Long eventId);

    /**
     * Adds Event in Compilation.
     * @param compId Compilation identifier.
     * @param eventId Event identifier.
     */
    void addEvent(Long compId, Long eventId);

    /**
     * Unpin Compilation
     * @param compId Compilation identifier.
     */
    void deletePinned(Long compId);

    /**
     * Pin Compilation
     * @param compId Compilation identifier.
     */
    void pin(Long compId);

    /**
     * Getting a compilation by id.
     * @param id Compilation identifier.
     * @return Compilation object.
     */
    Compilation get(Long id);

    /**
     * Getting pinned or not a list of compilations by params.
     * @param pinned pinned or not Compilation.
     * @param from integer value of the initial object on the page.
     * @param size integer number of objects on page.
     * @return list of compilations.
     */
    List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size);

}
