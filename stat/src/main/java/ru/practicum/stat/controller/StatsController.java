package ru.practicum.stat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stat.mapper.StatsMapper;
import ru.practicum.stat.service.StatsService;
import ru.practicum.stat.dto.ViewStat;
import ru.practicum.stat.dto.StatDto;
import ru.practicum.stat.model.Stat;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService service;
    private final StatsMapper mapper;

    @PostMapping("/hit")
    public void addStat(@RequestBody StatDto statDto) {
        Stat stat = mapper.toStats(statDto);
        service.addStat(stat);
    }

    @GetMapping("/stats")
    public List<ViewStat> getStats(
            @RequestParam String start,
            @RequestParam String end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(required = false, defaultValue = "false") boolean unique
    ) {
        String startDecoded = URLDecoder.decode(start, StandardCharsets.UTF_8);
        String endDecoded = URLDecoder.decode(end, StandardCharsets.UTF_8);

        return service.getStats(startDecoded, endDecoded, uris, unique);
    }

}
