package com.backend.vehicle_maintence_scheduler.dto;

import java.util.List;

public class ScheduleResponse {

    private int depotId;
    private int mechanicHours;
    private List<VehicleDTO> selectedTasks;
    private int totalImpact;
    private int totalDuration;

    public ScheduleResponse() {
    }

    public ScheduleResponse(int depotId, int mechanicHours, List<VehicleDTO> selectedTasks, int totalImpact, int totalDuration) {
        this.depotId = depotId;
        this.mechanicHours = mechanicHours;
        this.selectedTasks = selectedTasks;
        this.totalImpact = totalImpact;
        this.totalDuration = totalDuration;
    }

    public int getDepotId() {
        return depotId;
    }

    public void setDepotId(int depotId) {
        this.depotId = depotId;
    }

    public int getMechanicHours() {
        return mechanicHours;
    }

    public void setMechanicHours(int mechanicHours) {
        this.mechanicHours = mechanicHours;
    }

    public List<VehicleDTO> getSelectedTasks() {
        return selectedTasks;
    }

    public void setSelectedTasks(List<VehicleDTO> selectedTasks) {
        this.selectedTasks = selectedTasks;
    }

    public int getTotalImpact() {
        return totalImpact;
    }

    public void setTotalImpact(int totalImpact) {
        this.totalImpact = totalImpact;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }
}