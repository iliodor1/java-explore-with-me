package ru.practicum.main.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main.request.mapper.ParticipationRequestMapper;
import ru.practicum.main.request.service.ParticipationRequestService;
import ru.practicum.main.request.dto.ParticipationRequestDto;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}")
@RequiredArgsConstructor
public class ParticipationRequestController {
    private final ParticipationRequestService service;
    private final ParticipationRequestMapper mapper;

    @GetMapping("/requests")
    public List<ParticipationRequestDto> getUserRequests(
            @PathVariable(name = "userId") Long requesterId
    ) {
        return mapper.toDtos(service.getUserRequests(requesterId));
    }

    @PostMapping("/requests")
    public ParticipationRequestDto create(@PathVariable(name = "userId") Long requesterId,
                                          @RequestParam Long eventId) {
        return mapper.toDto(service.create(requesterId, eventId));

    }

    @PatchMapping("/requests/{requestId}/cancel")
    public ParticipationRequestDto cancel(@PathVariable Long userId,
                                          @PathVariable Long requestId) {
        return mapper.toDto(service.cancel(userId, requestId));
    }

    @GetMapping("/events/{eventId}/requests")
    public List<ParticipationRequestDto> getEventRequests(
            @PathVariable Long userId,
            @PathVariable Long eventId
    ) {
        return mapper.toDtos(service.getEventRequests(userId, eventId));
    }

    @PatchMapping("/events/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequest(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @PathVariable Long reqId
    ) {
        return mapper.toDto(service.confirmRequest(userId, eventId, reqId));
    }

    @PatchMapping("/events/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequest(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @PathVariable Long reqId
    ) {
        return mapper.toDto(service.rejectRequest(userId, eventId, reqId));
    }

}
