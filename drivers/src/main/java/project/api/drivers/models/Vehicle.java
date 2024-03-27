package project.api.drivers.models;

import com.google.api.client.util.Data;
import jakarta.persistence.*;
import java.util.List;

public abstract class Vehicle {
    private String Id;
    private List<Driver> driverList;
    private int capacity;
    private String fuelType;
    private String status;
    private Route route;
    private String vehicleType;
    private Data timeStart;
    private Data timeEnd;

    public Vehicle() {
    }

    public Vehicle(String id, List<Driver> driverList, int capacity, String fuelType, String status, Route route, String vehicleType, Data timeStart, Data timeEnd) {
        Id = id;
        this.driverList = driverList;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.status = status;
        this.route = route;
        this.vehicleType = vehicleType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Data getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Data timeStart) {
        this.timeStart = timeStart;
    }

    public Data getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Data timeEnd) {
        this.timeEnd = timeEnd;
    }
}
