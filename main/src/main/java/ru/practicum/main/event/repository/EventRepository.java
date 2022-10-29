package ru.practicum.main.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.main.event.model.Event;
import ru.practicum.main.event.model.State;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByInitiatorId(Long id, Pageable pageable);

    @Query("select e from Event e " +
            "where (:users is null or e.initiator.id in :users) " +
            "and (:states is null or e.state in :states) " +
            "and (:categories is null or e.category.id in :categories)")
    List<Event> findByParams(
            @Param("users") Collection<Long> users,
            @Param("states") Collection<State> states,
            @Param("categories") Collection<Long> categories,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (:users is null or e.initiator.id in :users) " +
            "and (:states is null or e.state in :states) " +
            "and (:categories is null or e.category.id in :categories) " +
            "and (e.eventDate >= :start)")
    List<Event> findByParamsWithRangeStart(
            @Param("users") Collection<Long> users,
            @Param("states") Collection<State> states,
            @Param("categories") Collection<Long> categories,
            @Param("start") LocalDateTime start,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (:users is null or e.initiator.id in :users) " +
            "and (:states is null or e.state in :states) " +
            "and (:categories is null or e.category.id in :categories) " +
            "and (e.eventDate <= :end)")
    List<Event> findByParamsWithRangeEnd(
            @Param("users") Collection<Long> users,
            @Param("states") Collection<State> states,
            @Param("categories") Collection<Long> categories,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (:users is null or e.initiator.id in :users) " +
            "and (:states is null or e.state in :states) " +
            "and (:categories is null or e.category.id in :categories) " +
            "and (e.eventDate >= :start)" +
            "and (e.eventDate <= :end)")
    List<Event> findByParams(
            @Param("users") Collection<Long> users,
            @Param("states") Collection<State> states,
            @Param("categories") Collection<Long> categories,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );

    Optional<Event> findByInitiator_IdAndId(Long userId, Long eventId);

    Optional<Event> findByIdAndState(Long eventId, State state);

    @Query("select e from Event e " +
            "where (:text is null " +
            "   or upper(e.description) like upper(concat('%', :text, '%')) " +
            "   or upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "and (:categoriesId is null or e.category.id in :categoriesId) " +
            "and (e.paid = :paid) " +
            "and (e.eventDate >= :start) " +
            "and (e.eventDate <= :end) " +
            "and (e.requests.size < e.participantLimit)")
    List<Event> findPublishedAvailableEventsByParams(
            @Param("text") String text,
            @Param("categoriesId") List<Long> categories,
            @Param("paid") Boolean paid,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (:text is null " +
            "   or upper(e.description) like upper(concat('%', :text, '%')) " +
            "   or upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "and (:categoriesId is null or e.category.id in :categoriesId) " +
            "and (e.paid = :paid) " +
            "and (e.eventDate >= :start) " +
            "and (e.eventDate <= :end) " +
            "and (e.requests.size >= e.participantLimit)")
    List<Event> findPublishedNotAvailableEventsByParams(
            @Param("text") String text,
            @Param("categoriesId") List<Long> categories,
            @Param("paid") Boolean paid,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (:text is null " +
            "   or upper(e.description) like upper(concat('%', :text, '%')) " +
            "   or upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "and (:categoriesId is null or e.category.id in :categoriesId) " +
            "and (e.paid = :paid) " +
            "and (e.eventDate >= :start) " +
            "and (e.requests.size < e.participantLimit)")
    List<Event> findPublishedAvailableEventsByParams(
            @Param("text") String text,
            @Param("categoriesId") List<Long> categories,
            @Param("paid") Boolean paid,
            @Param("start") LocalDateTime start,
            Pageable pageable
    );

    @Query("select e from Event e " +
            "where (:text is null " +
            "   or upper(e.description) like upper(concat('%', :text, '%')) " +
            "   or upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "and (:categoriesId is null or e.category.id in :categoriesId) " +
            "and (e.paid = :paid) " +
            "and (e.eventDate >= :start) " +
            "and (e.requests.size >= e.participantLimit)")
    List<Event> findPublishedNotAvailableEventsByParams(
            @Param("text") String text,
            @Param("categoriesId") List<Long> categories,
            @Param("paid") Boolean paid,
            @Param("start") LocalDateTime start,
            Pageable pageable
    );
}
