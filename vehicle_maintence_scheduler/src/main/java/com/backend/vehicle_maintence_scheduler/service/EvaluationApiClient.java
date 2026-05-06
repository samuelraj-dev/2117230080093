package com.backend.vehicle_maintence_scheduler.service;

import com.backend.logging_middleware.service.AuthService;
import com.backend.logging_middleware.service.LoggingService;
import com.backend.vehicle_maintence_scheduler.dto.DepotDTO;
import com.backend.vehicle_maintence_scheduler.dto.DepotsResponse;
import com.backend.vehicle_maintence_scheduler.dto.VehicleDTO;
import com.backend.vehicle_maintence_scheduler.dto.VehiclesResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EvaluationApiClient {

    private static final String DEPOTS_URL =
            "http://20.207.122.201/evaluation-service/depots";

    private static final String VEHICLES_URL =
            "http://20.207.122.201/evaluation-service/vehicles";

    private final RestTemplate restTemplate;
    private final AuthService authService;
    private final LoggingService loggingService;

    public EvaluationApiClient(
            RestTemplate restTemplate,
            AuthService authService,
            LoggingService loggingService
    ) {
        this.restTemplate = restTemplate;
        this.authService = authService;
        this.loggingService = loggingService;
    }

    public List<DepotDTO> fetchDepots() {

        loggingService.log(
                "backend",
                "info",
                "service",
                "Fetching depots from evaluation server"
        );

        String token = authService.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<DepotsResponse> response =
                restTemplate.exchange(
                        DEPOTS_URL,
                        HttpMethod.GET,
                        entity,
                        DepotsResponse.class
                );

        loggingService.log(
                "backend",
                "info",
                "service",
                "Successfully fetched depots"
        );

        return response.getBody().getDepots();
    }

    public List<VehicleDTO> fetchVehicles() {

        loggingService.log(
                "backend",
                "info",
                "service",
                "Fetching vehicles from evaluation server"
        );

        String token = authService.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<VehiclesResponse> response =
                restTemplate.exchange(
                        VEHICLES_URL,
                        HttpMethod.GET,
                        entity,
                        VehiclesResponse.class
                );

        loggingService.log(
                "backend",
                "info",
                "service",
                "Successfully fetched vehicles"
        );

        return response.getBody().getVehicles();
    }
}
