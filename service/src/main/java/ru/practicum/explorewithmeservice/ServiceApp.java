package ru.practicum.explorewithmeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }

}
