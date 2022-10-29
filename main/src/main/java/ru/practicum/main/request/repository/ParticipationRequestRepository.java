package ru.practicum.main.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main.request.model.ParticipationRequest;
import ru.practicum.main.request.model.Status;

import java.util.List;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    @Query("select p from ParticipationRequest p where p.user.id = ?1 and p.event.initiator.id <> ?1")
    List<ParticipationRequest> findUserRequests(Long id);

    List<ParticipationRequest> findByEventIdAndEventInitiatorId(Long eventId, Long userId);

    Integer countByEventIdAndStatus(Long eventId, Status status);

    @Transactional
    @Modifying
    @Query("update ParticipationRequest p set p.status = 'REJECTED' where p.event.id = ?1 and p.status = ?2")
    void cancelRequests(Long eventId, Status status);

    @Query("select count(p) from ParticipationRequest p where p.event.id = ?1 and p.status = ?2")
    long countEventConfirmedRequests(Long eventId, Status status);

}
