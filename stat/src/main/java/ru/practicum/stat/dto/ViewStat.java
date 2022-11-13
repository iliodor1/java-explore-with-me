package ru.practicum.stat.dto;

import lombok.Data;
import ru.practicum.stat.model.Stat;

import java.io.Serializable;

/**
 * A DTO for the {@link Stat} entity
 */
@Data
public class ViewStat implements Serializable {
    private final String app;
    private final String uri;
    private final Long hits;

}
