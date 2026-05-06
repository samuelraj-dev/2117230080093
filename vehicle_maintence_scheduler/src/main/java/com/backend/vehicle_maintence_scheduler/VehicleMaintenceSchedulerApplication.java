package com.backend.vehicle_maintence_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.backend.vehicle_maintence_scheduler",
                "com.backend.logging_middleware"
        }
)
public class VehicleMaintenceSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleMaintenceSchedulerApplication.class, args);
    }

}
