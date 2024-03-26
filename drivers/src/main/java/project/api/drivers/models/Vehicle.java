package project.api.drivers.models;

import jakarta.persistence.*;
import java.util.List;

public abstract class Vehicle {
    private String Id;
    private List<Driver> driverList;
    private int capacity;
    private String fuelType;
    private String status;
    private String route;
    private String vehicleType;

    public Vehicle(String id, List<Driver> driverList, int capacity, String fuelType, String status, String route, String vehicleType) {
        Id = id;
        this.driverList = driverList;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.status = status;
        this.route = route;
        this.vehicleType = vehicleType;
        if (vehicleType.equals("Coach")) {
            new Coach();
        } else if (vehicleType.equals("Container")) {
            new Container();
        }
        else {
            System.out.println("không có loại xe");
        }
    }
    public Vehicle() {
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
        if (vehicleType.equals("Coach")) {
            new Coach();
        } else if (vehicleType.equals("Container")) {
            new Container();
        }
        else {
            System.out.println("không có loại xe");
        }
    }

}
