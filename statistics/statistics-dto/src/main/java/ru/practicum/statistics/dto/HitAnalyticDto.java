package ru.practicum.statistics.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HitAnalyticDto {
    private String app;
    private String uri;
    private Long hits;
}
