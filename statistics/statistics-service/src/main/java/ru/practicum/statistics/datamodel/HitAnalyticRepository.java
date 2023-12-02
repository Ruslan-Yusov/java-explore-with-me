package ru.practicum.statistics.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HitAnalyticRepository extends JpaRepository<HitAnalyticEntity, Integer> {
    @Query(value = "select min(id) id, case when :unique then count(distinct ip) else count(*) end as count," +
            " uri, app from hit h" +
            " where h.time_stamp >= :dt_from and h.time_stamp <= :dt_to and (uri in :uris)" +
            " group by uri, app", nativeQuery = true)
    List<HitAnalyticEntity> getListByUri(
            @Param("dt_from") LocalDateTime dtFrom,
            @Param("dt_to") LocalDateTime dtTo,
            @Param("unique") Boolean unique,
            @Param("uris") List<String> uris
    );

    @Query(value = "select min(id) id, case when :unique then count(distinct ip) else count(*) end as count," +
            " uri, app from hit h" +
            " where h.time_stamp >= :dt_from and h.time_stamp <= :dt_to " +
            " group by uri, app", nativeQuery = true)
    List<HitAnalyticEntity> getList(
            @Param("dt_from") LocalDateTime dtFrom,
            @Param("dt_to") LocalDateTime dtTo,
            @Param("unique") Boolean unique
    );
}
