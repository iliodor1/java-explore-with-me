package ru.practicum.stat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stat.dto.ViewStat;
import ru.practicum.stat.exeptions.BadRequestException;
import ru.practicum.stat.model.Stat;
import ru.practicum.stat.repository.StatsRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;

    @Override
    public void addStat(Stat stat) {
        if (stat == null) {
            throw new BadRequestException("Stat should be not null");
        }

        repository.save(stat);
    }

    @Override
    public List<ViewStat> getStats(String start, String end, List<String> uris, boolean unique) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);

        return !unique
                ? repository.getByParams(startDateTime, endDateTime, uris)
                : repository.getUniqueByParams(startDateTime, endDateTime, uris);
    }

}
