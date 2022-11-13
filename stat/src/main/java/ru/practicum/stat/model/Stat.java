package ru.practicum.stat.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Stat is the entity we'll be using to store information about endpoints stats.
 *
 * @author Eldar Gainanov
 */

@Entity
@Getter
@Setter
@Table(name = "stats")
public class Stat {

    /**
     * The Stat identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * App is a name of a service whose information is being recorded.
     */
    @Column(name = "app", nullable = false)
    private String app;

    /**
     * URI for which the request was made.
     */
    @Column(name = "uri")
    private String uri;

    /**
     *ip address of the user who made the request
     */
    @Column(name = "ip", nullable = false)
    private String ip;

    /**
     * Date and time when the request was made to the endpoint.
     */
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Stat stat = (Stat) o;
        return id != null && Objects.equals(id, stat.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
