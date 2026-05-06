package com.backend.vehicle_maintence_scheduler.service;

import com.backend.logging_middleware.service.LoggingService;
import com.backend.vehicle_maintence_scheduler.dto.DepotDTO;
import com.backend.vehicle_maintence_scheduler.dto.ScheduleResponse;
import com.backend.vehicle_maintence_scheduler.dto.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerService {

    private final ExternalApiClient externalApiClient;
    private final LoggingService loggingService;

    public SchedulerService(
            ExternalApiClient externalApiClient,
            LoggingService loggingService
    ) {
        this.externalApiClient = externalApiClient;
        this.loggingService = loggingService;
    }

    public ScheduleResponse schedule(int depotId) {

        loggingService.log(
                "backend",
                "info",
                "service",
                "Starting vehicle scheduling for depot " + depotId
        );

        List<DepotDTO> depots =
                externalApiClient.fetchDepots();

        DepotDTO selectedDepot = depots.stream()
                .filter(d -> d.getId() == depotId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Depot not found"));

        int maxHours = selectedDepot.getMechanicHours();

        List<VehicleDTO> vehicles =
                externalApiClient.fetchVehicles();

        // I am sorting vehicles by dividing impact by duration
        // This gives a neutralized priority taking both impact and duration into account
        vehicles.sort((a, b) -> {

            double x =
                    (double) a.getImpact() / a.getDuration();

            double y =
                    (double) b.getImpact() / b.getDuration();

            if (x < y) {
                return 1;
            } else if (x > y) {
                return -1;
            }

            return 0;
        });

        List<VehicleDTO> selectedTasks = new ArrayList<>();

        int totalDuration = 0;
        int totalImpact = 0;

        for (VehicleDTO vehicle : vehicles) {
            if (totalDuration + vehicle.getDuration() <= maxHours) {
                selectedTasks.add(vehicle);
                totalDuration += vehicle.getDuration();
                totalImpact += vehicle.getImpact();
            }
        }

        loggingService.log(
                "backend",
                "info",
                "service",
                "Vehicle scheduling completed for depot " + depotId
        );

        return new ScheduleResponse(
                depotId,
                maxHours,
                selectedTasks,
                totalImpact,
                totalDuration
        );
    }
}