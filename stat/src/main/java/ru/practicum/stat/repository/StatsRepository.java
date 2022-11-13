package ru.practicum.stat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.stat.dto.ViewStat;
import ru.practicum.stat.model.Stat;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stat, Long> {
    @Query("select new ru.practicum.stat.dto.ViewStat(s.app, s.uri, count(s.ip)) " +
            "from Stat s " +
            "where :uri is null or s.uri = :uri " +
            "and s.timestamp >= :start " +
            "and s.timestamp <= :end " +
            "group by s.app, s.uri ")
    List<ViewStat> getByParams(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uri") List<String> uri
    );

    @Query("select new ru.practicum.stat.dto.ViewStat(s.app, s.uri, count(distinct s.ip)) " +
            "from Stat s " +
            "where :uri is null or s.uri = :uri " +
            "and s.timestamp >= :start " +
            "and s.timestamp <= :end " +
            "group by s.app, s.uri ")
    List<ViewStat> getUniqueByParams(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("uri") List<String> uri
    );

}
