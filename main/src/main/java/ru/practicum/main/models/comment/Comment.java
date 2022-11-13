package ru.practicum.main.models.comment;

import lombok.*;
import org.hibernate.Hibernate;
import ru.practicum.main.models.event.Event;
import ru.practicum.main.models.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Comment is the entity that will be used to store information about the event's comment.
 *
 * @author Eldar Gainanov
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@RequiredArgsConstructor
public class Comment {
    /**
     * The comment identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * The comment text.
     */
    @Column(name = "text", nullable = false, length = 7000)
    private String text;
    /**
     * The comment status.
     */
    @Column(name = "status", nullable = false)
    private CommentStatus status;
    /**
     * Date and time the comment was created.
     */
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    /**
     * {@link Event} to which the comment was left
     */
    @JoinColumn(name = "event_id", nullable = false)
    @ManyToOne
    private Event event;
    /**
     * Comment author({@link User})
     */
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
