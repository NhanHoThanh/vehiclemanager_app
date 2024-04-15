package project.api.drivers.models;

import java.util.Date;
import java.util.List;

public class CoachVehicle {
    private String idVehicle;
    private List<String> driverList;
    private int capacity;
    private String fuelType;
    private String status;
    private String route;
    private String vehicleType;
    private Date timeStart;
    private Date timeEnd;
    private  String destination;
    private String departure;
    private int numberOfSeats;
    private int numberOfPassenger;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private List<String> emptySeat;
    private List<String> passengerList;

    public CoachVehicle() {
    }

    public CoachVehicle(String idVehicle, List<String> driverList, int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd, String destination, String departure, int numberOfSeats, int numberOfPassenger, Date previousMaintenanceDate, Date nextMaintenanceDate, List<String> emptySeat, List<String> passengerList) {
        this.idVehicle = idVehicle;
        this.driverList = driverList;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.status = status;
        this.route = route;
        this.vehicleType = vehicleType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.destination = destination;
        this.departure = departure;
        this.numberOfSeats = numberOfSeats;
        this.numberOfPassenger = numberOfPassenger;
        this.previousMaintenanceDate = previousMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.emptySeat = emptySeat;
        this.passengerList = passengerList;
    }

    public String getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public List<String> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<String> driverList) {
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

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
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
}
