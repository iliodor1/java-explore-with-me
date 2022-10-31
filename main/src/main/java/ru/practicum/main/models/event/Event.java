package ru.practicum.main.models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;
import ru.practicum.main.models.request.ParticipationRequest;
import ru.practicum.main.models.user.User;
import ru.practicum.main.models.category.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Event is the main entity we'll be using to store information about the event.
 *
 * @author Eldar Gainanov
 */
@Entity
@Table(name = "events")
@Getter
@Setter
@RequiredArgsConstructor
@DynamicUpdate
public class Event {
    /**
     * The event identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *Short description of the event.
     */
    @Column(name = "annotation", length = 2000, nullable = false)
    private String annotation;
    /**
     * The {@link Category} to which the event belongs.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     *Date and time the event was created.
     */
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    /**
     * Full description of the event.
     */
    @Column(name = "description", length = 7000, nullable = false)
    private String description;

    /**
     * Date and time of the event.
     */
    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    /**
     * The event initiator.
     */
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    /**
     * The event location latitude.
     */
    @Column(name = "lat")
    private Float lat;

    /**
     * The event location longitude.
     */
    @Column(name = "lon")
    private Float lon;

    /**
     * Participation of the event is paid (true) or free (false)
     */
    @Column(name = "paid", nullable = false)
    private Boolean paid;

    /**
     * Limit on the number of participants. Value 0 - means no limit.
     */
    @Column(name = "participant_limit", nullable = false)
    private Integer participantLimit;

    /**
     * The event publication date and time.
     */
    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    /**
     *Preliminary moderation of applications.
     */
    @Column(name = "request_moderation", nullable = false)
    private Boolean requestModeration;

    /**
     *  The event life cycle states {@link State}
     */
    @Column(name = "state")
    private State state;

    /**
     *The event title.
     */
    @Column(name = "title", length = 120, nullable = false)
    private String title;

    /**
     * List of requests for participation in the event.
     */
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    Set<ParticipationRequest> requests = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
