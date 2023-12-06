package ru.practicum.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.statistics.dto.HitAnalyticDto;
import ru.practicum.statistics.dto.HitRecordDto;
import ru.practicum.statistics.service.HitService;

import java.util.List;

@RestController
public class HitController {
    @Autowired
    private HitService hitService;

    @GetMapping("/stats")
    public List<HitAnalyticDto> getStats(
            @RequestParam(name = "start")
            String dateFrom,
            @RequestParam(name = "end")
            String dateTo,
            @RequestParam(name = "uris", required = false)
            List<String> uris,
            @RequestParam(name = "unique", defaultValue = "false") Boolean unique) {
        return hitService.getStats(dateFrom, dateTo, uris, unique);
    }

    @PostMapping("/hit")
    public ResponseEntity<Void> postHit(
            @RequestBody HitRecordDto body
    ) {
        hitService.add(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
