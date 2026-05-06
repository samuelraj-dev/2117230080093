package com.backend.logging_middleware.service;

import com.backend.logging_middleware.dto.AuthRequest;
import com.backend.logging_middleware.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    private String token;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getToken() {

        if (token != null) {
            return token;
        }

        AuthRequest request = new AuthRequest(
                "samuelraj.h.2023.csbs@ritchennai.edu.in",
                "Samuel Raj H",
                "2117230080093",
                "BTCDqT",
                "8f1ff1fd-ca14-48d9-9dc9-1500b247171d",
                "ddAkjhtRcamDxAxF"
        );

        ResponseEntity<AuthResponse> response =
                restTemplate.postForEntity(
                        "http://20.207.122.201/evaluation-service/auth",
                        request,
                        AuthResponse.class
                );

        token = response.getBody().getAccessToken();

        return token;
    }
}