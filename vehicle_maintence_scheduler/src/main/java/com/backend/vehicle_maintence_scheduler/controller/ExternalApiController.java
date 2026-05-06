package com.backend.vehicle_maintence_scheduler.controller;

import com.backend.vehicle_maintence_scheduler.dto.DepotDTO;
import com.backend.vehicle_maintence_scheduler.dto.ScheduleResponse;
import com.backend.vehicle_maintence_scheduler.dto.VehicleDTO;
import com.backend.vehicle_maintence_scheduler.service.ExternalApiClient;
import com.backend.vehicle_maintence_scheduler.service.SchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {
    private final ExternalApiClient externalApiClient;

    public ExternalApiController(
            ExternalApiClient externalApiClient
    ) {
        this.externalApiClient = externalApiClient;
    }

    @GetMapping("/depots")
    public List<DepotDTO> depots() {
        return externalApiClient.fetchDepots();
    }

    @GetMapping("/vehicles")
    public List<VehicleDTO> vehicles() {
        return externalApiClient.fetchVehicles();
    }
}
