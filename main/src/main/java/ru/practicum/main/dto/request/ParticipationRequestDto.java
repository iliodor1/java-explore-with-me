package ru.practicum.main.dto.request;

import lombok.Data;
import ru.practicum.main.models.request.ParticipationRequest;

import java.io.Serializable;

/**
 * A DTO for the {@link ParticipationRequest} entity
 */
@Data
public class ParticipationRequestDto implements Serializable {
    /**
     * The ParticipationRequest identifier.
     */
    private final Long id;
    /**
     * Date and time the request was created.
     */
    private final String created;
    /**
     *The event identifier
     */
    private final Long event;
    /**
     *The user identifier
     */
    private final Long requester;
    /**
     *The request status
     */
    private final String status;

}
