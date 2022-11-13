package ru.practicum.main.services.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.event.State;
import ru.practicum.main.exceptions.BadRequestException;
import ru.practicum.main.exceptions.ConflictRequestException;
import ru.practicum.main.exceptions.NotFoundException;
import ru.practicum.main.models.request.ParticipationRequest;
import ru.practicum.main.models.request.Status;
import ru.practicum.main.repositories.request.ParticipationRequestRepository;
import ru.practicum.main.models.user.User;
import ru.practicum.main.services.event.EventService;
import ru.practicum.main.services.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipationRequestServiceImpl implements ParticipationRequestService {
    private final ParticipationRequestRepository repository;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public ParticipationRequest create(Long requesterId, Long eventId) {
        User requester = userService.getUser(requesterId);
        Event event = eventService.getEvent(eventId);

        if (requester.equals(event.getInitiator())) {
            log.error("The initiator id {} cannot send a request to own event.", event.getInitiator());
            throw new ConflictRequestException("The initiator cannot send a request to own event.");
        } else if (!event.getState().equals(State.PUBLISHED)) {
            log.error("The event id {} must have a state 'PUBLISHED'", eventId);
            throw new ConflictRequestException("The event must have a state 'PUBLISHED'");
        } else if (event.getParticipantLimit().equals(countConfirmedRequests(eventId))) {
            log.error("The limit of participants has been reached.");
            throw new ConflictRequestException("The limit of participants has been reached.");
        }

        ParticipationRequest request = new ParticipationRequest();

        request.setUser(requester);
        request.setEvent(event);

        request.setCreated(LocalDateTime.now());
        if (event.getRequestModeration()) {
            request.setStatus(Status.PENDING);
        } else {
            request.setStatus(Status.CONFIRMED);
        }

        return repository.save(request);
    }

    @Override
    public ParticipationRequest cancel(Long userId, Long requestId) {
        ParticipationRequest request = getRequest(requestId);

        if (request.getUser().getId().equals(userId)) {
            request.setStatus(Status.CANCELED);

            return repository.save(request);
        } else {
            log.error("User id '{}' cannot canceled not own request.", userId);
            throw new BadRequestException("Only requester can canceled request.");
        }
    }

    @Override
    public List<ParticipationRequest> getEventRequests(Long userId, Long eventId) {
        return repository.findByEventIdAndEventInitiatorId(eventId, userId);
    }

    @Override
    public ParticipationRequest confirmRequest(Long userId, Long eventId, Long reqId) {
        Event event = eventService.getEvent(eventId);
        ParticipationRequest request = getRequest(reqId);

        if (!event.getInitiator().getId().equals(userId)) {
            log.error("User id '{}' cannot confirm request not own event.", userId);
            throw new BadRequestException("Only event initiator can confirm request.");
        }

        if (event.getParticipantLimit() == 0 || !event.getRequestModeration()) {
            request.setStatus(Status.CONFIRMED);

            return repository.save(request);
        }

        long confirmedRequests = repository.countByEventIdAndStatus(eventId, Status.CONFIRMED);

        if (confirmedRequests < event.getParticipantLimit()) {
            request.setStatus(Status.CONFIRMED);

            if (++confirmedRequests == event.getParticipantLimit()) {
                repository.cancelRequests(eventId, Status.PENDING);
            }
        } else {
            request.setStatus(Status.CANCELED);

        }
        return repository.save(request);
    }

    @Override
    public ParticipationRequest rejectRequest(
            Long userId,
            Long eventId,
            Long reqId
    ) {
        Event event = eventService.getEvent(eventId);
        ParticipationRequest request
                = repository.findById(reqId)
                            .orElseThrow(() -> {
                                log.error("The request id '{}' not found", reqId);
                                throw new NotFoundException("The required object was not found.");
                            });

        if (!event.getInitiator().getId().equals(userId)) {
            log.error("User id '{}' cannot reject request not own event.", userId);
            throw new BadRequestException("Only event initiator can reject request.");
        }

        request.setStatus(Status.REJECTED);
        return repository.save(request);
    }


    @Override
    public List<ParticipationRequest> getUserRequests(Long requesterId) {
        return repository.findUserRequests(requesterId);
    }

    private ParticipationRequest getRequest(Long reqId) {
        return repository.findById(reqId)
                         .orElseThrow(() -> {
                             log.error("The request id '{}' not found", reqId);
                             throw new NotFoundException("The required object was not found.");
                         });
    }

    private Integer countConfirmedRequests(Long eventId) {
        return repository.countByEventIdAndStatus(eventId, Status.CONFIRMED);
    }

}
