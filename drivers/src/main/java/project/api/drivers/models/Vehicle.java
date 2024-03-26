package project.api.drivers.models;

import jakarta.persistence.*;
import java.util.List;

public class Vehicle {
    private String Id;
    private List<Driver> driverList;
    private int capacity;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Vehicle(String id, List<Driver> driverList, int capacity, String fuelType, String status, String route, String vehicleType) {
        Id = id;
        this.driverList = driverList;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.status = status;
        this.route = route;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {
    }

    private String fuelType;
    private String status;
    private String route;
    private String vehicleType;

}
