package ru.practicum.main.dto.stat;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class StatDto implements Serializable {
    private final Long id;
    private final String app;
    private final String uri;
    private final String ip;
    private final String timestamp;

}
