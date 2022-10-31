package ru.practicum.main.services.request;

import ru.practicum.main.models.request.ParticipationRequest;

import java.util.List;

public interface ParticipationRequestService {

    /**
     * Adds a new ParticipationRequest.
     * @param requesterId: ParticipationRequest identifier.
     * @param eventId: Event identifier.
     * @return ParticipationRequest object that was created.
     */
    ParticipationRequest create(Long requesterId, Long eventId);

    /**
     * Setting cancel status for ParticipationRequest.
     * @param userId User identifier.
     * @param requestId ParticipationRequest identifier.
     * @return ParticipationRequest with cancel status.
     */
    ParticipationRequest cancel(Long userId, Long requestId);

    /**
     * Getting ParticipationRequest by requester id.
     * @param requesterId requester identifier.
     * @return list of ParticipationRequest.
     */
    List<ParticipationRequest> getUserRequests(Long requesterId);

    /**
     * Getting ParticipationRequest by event initiator id and event id.
     * @param userId event initiator identifier.
     * @param eventId event identifier.
     * @return list of ParticipationRequest.
     */
    List<ParticipationRequest> getEventRequests(Long userId, Long eventId);

    /**
     * Setting confirm status for ParticipationRequest.
     * @param userId event initiator identifier.
     * @param eventId event identifier.
     * @param reqId ParticipationRequest identifier.
     * @return ParticipationRequest with confirm status.
     */
    ParticipationRequest confirmRequest(Long userId, Long eventId, Long reqId);

    /**
     * Setting reject status for ParticipationRequest.
     * @param userId event initiator identifier.
     * @param eventId event identifier.
     * @param reqId ParticipationRequest identifier.
     * @return ParticipationRequest with reject status.
     */
    ParticipationRequest rejectRequest(Long userId, Long eventId, Long reqId);

}
