package project.api.drivers.models;
import java.util.Date;
import java.util.List;


public class Vehicle {
    private String idVehicle;
    private List<String> driverList;
    private int capacity;
    private String fuelType;
    private String status;
    private String route;
    private String vehicleType; // chỉ có 2 giá trị coach và container
    private Date timeStart;
    private Date timeEnd;
    private  String destination;
    private String departure;

    public Vehicle() {
    }

    public Vehicle(String idVehicle, List<String> driverList, int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd, String destination, String departure) {
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
    public void addDriver(String id) {
        this.driverList.add(id);
    }
    public  void removeDriver(Integer id) {
        this.driverList.remove(id);
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
}
