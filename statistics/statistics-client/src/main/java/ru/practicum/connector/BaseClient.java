package ru.practicum.connector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.practicum.statistics.dto.HitAnalyticDto;
import ru.practicum.statistics.dto.HitRecordDto;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class BaseClient {
    private final RestTemplate rest = new RestTemplate();
    @Value("${statistics-client.client.url}")
    private String baseUrl;

    public boolean postHit(HitRecordDto hitRecordDto) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(hitRecordDto);

        HitRecordDto response = rest.postForObject(URI.create(baseUrl + "/hit"), requestEntity, HitRecordDto.class);

        System.out.println(response);

        assert response != null;
        return (response.getUri().equals(hitRecordDto.getUri()));
    }

    public List<HitAnalyticDto> getStat(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        return getResponse(start, end, uris, unique);
    }

    public List<HitAnalyticDto> getStat(LocalDateTime start, LocalDateTime end, List<String> uris) {
        return getResponse(start, end, uris, null);
    }

    public List<HitAnalyticDto> getStat(LocalDateTime start, LocalDateTime end, Boolean unique) {
        return getResponse(start, end, null, unique);
    }

    public List<HitAnalyticDto> getStat(LocalDateTime start, LocalDateTime end) {
        return getResponse(start, end, null, null);
    }

    private List<HitAnalyticDto> getResponse(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        String startTime;
        String endTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);

        try {
            startTime = Objects.requireNonNull(start).format(formatter);
            endTime = Objects.requireNonNull(end).format(formatter);
        } catch (NullPointerException e) {
            throw new RuntimeException("'start' and 'end' fields must not be null");
        }

        Map<String, Object> parameters = new java.util.HashMap<>(Map.of(
                "start", startTime,
                "end", endTime
        ));

        HitAnalyticDto[] statDtos = rest.getForObject(makePath(baseUrl, uris, unique), HitAnalyticDto[].class, parameters);

        return Arrays.asList(Objects.requireNonNull(statDtos));
    }

    private String makePath(String path, List<String> uris, Boolean unique) {
        StringBuilder requestPath = new StringBuilder(path);

        requestPath.append("/stats?start={start}&end={end}");

        if (uris != null && !uris.isEmpty()) {
            for (String uri : uris) {
                requestPath.append("&uris=").append(uri);
            }
        }

        if (unique != null) {
            requestPath.append("&unique=").append(unique);
        }

        return requestPath.toString();
    }
}
