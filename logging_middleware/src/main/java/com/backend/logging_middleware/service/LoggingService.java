package com.backend.logging_middleware.service;

import com.backend.logging_middleware.dto.LogRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoggingService {

    private final RestTemplate restTemplate;
    private final AuthService authService;

    public LoggingService(
            RestTemplate restTemplate,
            AuthService authService
    ) {
        this.restTemplate = restTemplate;
        this.authService = authService;
    }

    public void log(
            String stack,
            String level,
            String packageName,
            String message
    ) {

        String token = authService.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        headers.setContentType(MediaType.APPLICATION_JSON);

        LogRequest request = new LogRequest(
                stack,
                level,
                packageName,
                message
        );

        HttpEntity<LogRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "http://20.207.122.201/evaluation-service/logs",
                        HttpMethod.POST,
                        entity,
                        String.class
                );

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }
}