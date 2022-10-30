package ru.practicum.main.services.request;

import ru.practicum.main.models.request.ParticipationRequest;

import java.util.List;

public interface ParticipationRequestService {
    ParticipationRequest create(Long requesterId, Long eventId);

    ParticipationRequest cancel(Long userId, Long requestId);

    List<ParticipationRequest> getUserRequests(Long requesterId);

    List<ParticipationRequest> getEventRequests(Long userId, Long eventId);

    ParticipationRequest confirmRequest(Long userId, Long eventId, Long reqId);

    ParticipationRequest rejectRequest(Long userId, Long eventId, Long reqId);

}
