package project.api.drivers.models;
import java.util.List;

import java.util.Date;


public class Coach extends Vehicle{
    private int numberOfSeats;
    private int numberOfPassenger;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private List<Integer> emptySeat;
    private Passenger Passenger;

    public Coach(String id, List<Driver> driverList, int capacity, String fuelType, String status, String route, String vehicleType, int numberOfSeats, int numberOfPassenger, Date previousMaintenanceDate, Date nextMaintenanceDate, List<Integer> emptySeat, project.api.drivers.models.Passenger passenger) {
        super(id, driverList, capacity, fuelType, status, route, vehicleType);
        this.numberOfSeats = numberOfSeats;
        this.numberOfPassenger = numberOfPassenger;
        this.previousMaintenanceDate = previousMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.emptySeat = emptySeat;
        Passenger = passenger;
    }

    public Coach() {
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

    public project.api.drivers.models.Passenger getPassenger() {
        return Passenger;
    }

    public void setPassenger(project.api.drivers.models.Passenger passenger) {
        Passenger = passenger;
    }
}
