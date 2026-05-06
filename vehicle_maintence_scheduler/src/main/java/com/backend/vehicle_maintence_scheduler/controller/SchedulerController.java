package com.backend.vehicle_maintence_scheduler.controller;

import com.backend.vehicle_maintence_scheduler.dto.ScheduleResponse;
import com.backend.vehicle_maintence_scheduler.service.SchedulerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(
            SchedulerService schedulerService
    ) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("/{depotId}")
    public ScheduleResponse schedule(
            @PathVariable int depotId
    ) {
        return schedulerService.schedule(depotId);
    }
}