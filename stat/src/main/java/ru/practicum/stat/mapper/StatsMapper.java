package ru.practicum.stat.mapper;

import org.mapstruct.*;
import ru.practicum.stat.dto.StatDto;
import ru.practicum.stat.dto.ViewStat;
import ru.practicum.stat.model.Stat;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StatsMapper {
    StatDto toDto(Stat stat);

    Stat toStats(ViewStat viewStat);

    @Mapping(target = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Stat toStats(StatDto statDto);

    ViewStat toViewDto(Stat stat);

    List<ViewStat> toViewDtos(List<Stat> stats);

}
