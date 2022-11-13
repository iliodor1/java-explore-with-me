package ru.practicum.stat.service;

import ru.practicum.stat.dto.ViewStat;
import ru.practicum.stat.model.Stat;

import java.util.List;

public interface StatsService {

    /**
     *Saving information that there was a request to the endpoint.
     * @param stat is {@link Stat} object.
     */
    void addStat(Stat stat);

    /**
     *Getting statistics on views.
     * @param start Date and time of the beginning of the range for which you want to unload stats.
     * @param end Date and time of the end of the range for which you want to unload stats.
     * @param uris List of uris for which statistics should be uploaded.
     * @param unique Consider only unique visits (only with a unique ip).
     * @return  List of {@link ViewStat}.
     */
    List<ViewStat> getStats(String start, String end, List<String> uris, boolean unique);

}
