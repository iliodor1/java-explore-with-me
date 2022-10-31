package ru.practicum.main.models.compilation;

import lombok.*;
import org.hibernate.Hibernate;
import ru.practicum.main.models.event.Event;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Compilation is the entity we'll be using to store information about events compilation.
 *
 * @author Eldar Gainanov
 */
@Entity
@Table(name = "compilations")
@Getter
@Setter
@RequiredArgsConstructor
public class Compilation {
    /**
     * The compilation identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     *The compilation pinned to the main page.
     */
    @Column(name = "pinned", nullable = false)
    private Boolean pinned;

    /**
     * The compilation title.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * List of events included in the compilation.
     */
    @ManyToMany
    @JoinTable(
            name = "events_compilation",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Compilation that = (Compilation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
