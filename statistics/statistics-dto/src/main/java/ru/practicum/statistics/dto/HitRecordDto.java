package ru.practicum.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@NoArgsConstructor
public class HitRecordDto {
    private String app;
    private String uri;
    private String ip;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
