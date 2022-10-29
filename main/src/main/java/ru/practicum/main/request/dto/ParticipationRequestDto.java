package ru.practicum.main.request.dto;

import lombok.Data;
import ru.practicum.main.request.model.ParticipationRequest;

import java.io.Serializable;

/**
 * A DTO for the {@link ParticipationRequest} entity
 */
@Data
public class ParticipationRequestDto implements Serializable {
    private final Long id;
    private final String created;
    private final Long event;
    private final Long requester;
    private final String status;

}
