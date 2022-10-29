package ru.practicum.stat.service;

import ru.practicum.stat.dto.ViewStat;
import ru.practicum.stat.model.Stat;

import java.util.List;

public interface StatsService {
    void addStat(Stat stat);

    List<ViewStat> getStats(String start, String end, List<String> uris, boolean unique);

}
