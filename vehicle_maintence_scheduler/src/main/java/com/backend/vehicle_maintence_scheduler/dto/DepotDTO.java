package com.backend.vehicle_maintence_scheduler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepotDTO {

    @JsonProperty("ID")
    private int id;

    @JsonProperty("MechanicHours")
    private int mechanicHours;

    public DepotDTO() {
    }

    public DepotDTO(int id, int mechanicHours) {
        this.id = id;
        this.mechanicHours = mechanicHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMechanicHours() {
        return mechanicHours;
    }

    public void setMechanicHours(int mechanicHours) {
        this.mechanicHours = mechanicHours;
    }
}