package ru.practicum.main.event.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ViewStat implements Serializable {
    private final String app;
    private final String uri;
    private final Long hits;

    @JsonCreator
    public ViewStat(
            @JsonProperty("name") String app,
            @JsonProperty("uri") String uri,
            @JsonProperty("hits") Long hits
    ) {
        this.app = app;
        this.uri = uri;
        this.hits = hits;
    }
}
