package ru.practicum.main.models.request;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ParticipationRequest is the entity we'll be using to store information about requests to participate in events.
 *
 * @author Eldar Gainanov
 */
@Entity
@Table(name = "request", uniqueConstraints =
    @UniqueConstraint(columnNames = {"event_id", "requester_id"}))
@Getter
@Setter
@ToString
@DynamicUpdate
public class ParticipationRequest {

    /**
     * The ParticipationRequest identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Date and time the request was created.
     */
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User user;

    /**
     *The request status
     */
    @Column(name = "status", nullable = false)
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipationRequest request = (ParticipationRequest) o;
        return id != null && Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
