package ru.practicum.main.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.main.dto.event.EventShortDto;
import ru.practicum.main.dto.stat.StatDto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventClient extends BaseClient {
    private static final LocalDateTime START = LocalDateTime.of(2020, Month.JANUARY, 1, 20, 0, 0);
    private static final LocalDateTime END = LocalDateTime.of(2035, Month.JANUARY, 1, 20, 0, 0);

    @Autowired
    public EventClient(@Value("${stat-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public void postStat(StatDto statDto) {
        post("/hit", statDto);
    }

    public ResponseEntity<Object> getViews(Long eventId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = URLEncoder.encode(START.format(formatter), StandardCharsets.UTF_8);
        String end = URLEncoder.encode(END.format(formatter), StandardCharsets.UTF_8);

        Map<String, Object> params = Map.of(
                "start", start,
                "end", end,
                "uris", "/events/" + eventId,
                "unique", true
        );

        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", params);
    }

    public ResponseEntity<Object> getViews(List<EventShortDto> events) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start = URLEncoder.encode(START.format(formatter), StandardCharsets.UTF_8);
        String end = URLEncoder.encode(END.format(formatter), StandardCharsets.UTF_8);

        List<String> uris = events.stream()
                                  .map(e -> "/events/" + e.getId())
                                  .collect(Collectors.toList());

        Map<String, Object> params = Map.of(
                "start", start,
                "end", end,
                "uris", uris,
                "unique", true
        );

        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", params);
    }

}
