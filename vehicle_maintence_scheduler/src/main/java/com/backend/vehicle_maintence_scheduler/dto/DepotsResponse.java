package com.backend.vehicle_maintence_scheduler.dto;

import java.util.List;

public class DepotsResponse {

    private List<DepotDTO> depots;

    public DepotsResponse(List<DepotDTO> depots) {
        this.depots = depots;
    }

    public List<DepotDTO> getDepots() {
        return depots;
    }

    public void setDepots(List<DepotDTO> depots) {
        this.depots = depots;
    }
}