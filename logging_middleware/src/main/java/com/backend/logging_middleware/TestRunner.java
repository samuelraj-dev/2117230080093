package com.backend.logging_middleware;

import com.backend.logging_middleware.service.LoggingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    private final LoggingService loggingService;

    public TestRunner(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public void run(String... args) {

//        loggingService.log(
//                "backend",
//                "info",
//                "service",
//                "middleware working"
//        );
//
//        System.out.println("log sent");
    }
}