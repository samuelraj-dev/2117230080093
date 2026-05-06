package com.backend.vehicle_maintence_scheduler.dto;

import java.util.List;

public class VehiclesResponse {
    private List<VehicleDTO> vehicles;

    public VehiclesResponse(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}