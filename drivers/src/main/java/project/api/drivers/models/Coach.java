package project.api.drivers.models;
import com.google.api.client.util.Data;

import java.util.List;

import java.util.Date;


public class Coach extends Vehicle{

    private int numberOfSeats;
    private int numberOfPassenger;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private List<Integer> emptySeat;
    private List<String> passengerList;

    public Coach() {
    }

    public Coach(String id, List<Integer> driverList, int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd, int numberOfSeats, int numberOfPassenger, Date previousMaintenanceDate, Date nextMaintenanceDate, List<Integer> emptySeat, List<String> passengerList) {
        super(id, driverList, capacity, fuelType, status, route, vehicleType, timeStart, timeEnd);
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

    public List<Integer> getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(List<Integer> emptySeat) {
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
