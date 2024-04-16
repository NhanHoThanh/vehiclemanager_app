package project.api.drivers.models;
import com.google.api.client.util.Data;

import java.util.List;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties({"capacity", "departure", "destination", "driverList", "fuelType", "idVehicle", "route", "status", "timeEnd", "timeStart", "vehicleType"})
public class Coach extends Vehicle{

    private int numberOfSeats;
    private int numberOfPassenger;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private List<String> emptySeat;
    private List<String> passengerList;

    public Coach() {
    }

    public Coach(int numberOfSeats, int numberOfPassenger, Date previousMaintenanceDate, Date nextMaintenanceDate, List<String> emptySeat, List<String> passengerList) {
        this.numberOfSeats = numberOfSeats;
        this.numberOfPassenger = numberOfPassenger;
        this.previousMaintenanceDate = previousMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.emptySeat = emptySeat;
        this.passengerList = passengerList;
    }

    public Coach(String idVehicle, List<String> driverList, int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd, String destination, String departure, int numberOfSeats, int numberOfPassenger, Date previousMaintenanceDate, Date nextMaintenanceDate, List<String> emptySeat, List<String> passengerList) {
        super(idVehicle, driverList, capacity, fuelType, status, route, vehicleType, timeStart, timeEnd, destination, departure);
        this.numberOfSeats = numberOfSeats;
        this.numberOfPassenger = numberOfPassenger;
        this.previousMaintenanceDate = previousMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.emptySeat = emptySeat;
        this.passengerList = passengerList;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(int numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }

    public Date getPreviousMaintenanceDate() {
        return previousMaintenanceDate;
    }

    public void setPreviousMaintenanceDate(Date previousMaintenanceDate) {
        this.previousMaintenanceDate = previousMaintenanceDate;
    }

    public Date getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(Date nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public List<String> getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(List<String> emptySeat) {
        this.emptySeat = emptySeat;
    }

    public List<String> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<String> passengerList) {
        this.passengerList = passengerList;
    }

    public void setVehicle(Vehicle vehicle) {
        // Set properties inherited from Vehicle class
        this.setIdVehicle(vehicle.getIdVehicle());
        this.setDriverList(vehicle.getDriverList());
        this.setCapacity(vehicle.getCapacity());
        this.setFuelType(vehicle.getFuelType());
        this.setStatus(vehicle.getStatus());
        this.setRoute(vehicle.getRoute());
        this.setVehicleType(vehicle.getVehicleType());
        this.setTimeStart(vehicle.getTimeStart());
        this.setTimeEnd(vehicle.getTimeEnd());
    }
}
