package ru.practicum.statistics.datamodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HitRecordRepository extends JpaRepository<HitRecordEntity, Integer> {
}
