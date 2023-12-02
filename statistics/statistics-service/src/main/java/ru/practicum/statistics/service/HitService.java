package ru.practicum.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.statistics.datamodel.HitAnalyticRepository;
import ru.practicum.statistics.datamodel.HitRecordEntity;
import ru.practicum.statistics.datamodel.HitRecordRepository;
import ru.practicum.statistics.dto.HitAnalyticDto;
import ru.practicum.statistics.dto.HitRecordDto;
import ru.practicum.statistics.exeption.BadRequestException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class HitService {
    @Autowired
    private HitRecordRepository hitRecordRepository;
    @Autowired
    private HitAnalyticRepository hitAnalyticRepository;

    public List<HitAnalyticDto> getStats(String dateFrom, String dateTo, List<String> uris, Boolean unique) {
        return (isEmpty(uris)
                ? hitAnalyticRepository.getList(convert(dateFrom), convert(dateTo), unique)
                : hitAnalyticRepository.getListByUri(convert(dateFrom), convert(dateTo), unique, uris)
        )
                .stream()
                .map(ent -> {
                    HitAnalyticDto dto = new HitAnalyticDto();
                    dto.setHits(ent.getCount());
                    dto.setApp(ent.getApp());
                    dto.setUri(ent.getUri());
                    return dto;
                })
                .sorted(Comparator.comparing(HitAnalyticDto::getHits).reversed())
                .collect(Collectors.toList());
    }

    public void add(HitRecordDto body) {
        HitRecordEntity hitRecordEntity = new HitRecordEntity();
        hitRecordEntity.setApp(body.getApp());
        hitRecordEntity.setUri(body.getUri());
        hitRecordEntity.setIp(body.getIp());
        hitRecordEntity.setTimestamp(body.getTimestamp());
        hitRecordRepository.save(hitRecordEntity);
    }

    private static LocalDateTime convert(String value) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            return LocalDateTime.parse(value, formatter);
        } catch (DateTimeParseException e) {
            throw new BadRequestException(String.format("Cant read date: invalid format (%s)", value));
        }
    }
}


