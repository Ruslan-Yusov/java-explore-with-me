package ru.practicum.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class StatisticsApp {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApp.class, args);
    }
}
