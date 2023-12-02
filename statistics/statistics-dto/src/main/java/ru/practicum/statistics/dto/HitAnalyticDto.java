package ru.practicum.statistics.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HitAnalyticDto {
    private String app;
    private String uri;
    private Long hits;
}
