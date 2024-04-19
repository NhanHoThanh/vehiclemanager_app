package project.api.drivers.models;
import java.util.List;
import java.util.Date;


public class Container extends Vehicle{
    private String cargoType;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private int currentLoad;
    private int maxLoad;
    private List<String> cargoList;

    public Container() {
    }

    public Container(int currentLoad, List<String> cargoList) {
        this.currentLoad = currentLoad;
        this.cargoList = cargoList;
    }

    public Container(String idVehicle, List<String> driverList, List<String> historyRouteList, List<String> historyIncomeList,
                     List<Date> timeStartList, List<Date> timeEndList, Double toTalRevenue, Double toTalProfit, Double toTalCost,
                     int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd,
                     String destination, String departure, String cargoType, Date previousMaintenanceDate, Date nextMaintenanceDate,
                     int currentLoad, int maxLoad, List<String> cargoList) {
        super(idVehicle, driverList, historyRouteList, historyIncomeList, timeStartList, timeEndList, toTalRevenue, toTalProfit, toTalCost, capacity, fuelType, status, route, vehicleType, timeStart, timeEnd, destination, departure);
        this.cargoType = cargoType;
        this.previousMaintenanceDate = previousMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.currentLoad = currentLoad;
        this.maxLoad = maxLoad;
        this.cargoList = cargoList;
    }

    public Container(String idVehicle, List<String> driverList, List<String> hisRouteList, List<String> hisIncomeList, List<Date> timeStartList, List<Date> timeEndList, int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd, String destination, String departure, String cargoType, Date previousMaintenanceDate, Date nextMaintenanceDate, int currentLoad, int maxLoad, List<String> cargoList) {
        super(idVehicle, driverList, hisRouteList, hisIncomeList, timeStartList, timeEndList, capacity, fuelType, status, route, vehicleType, timeStart, timeEnd, destination, departure);
        this.cargoType = cargoType;
        this.previousMaintenanceDate = previousMaintenanceDate;
        this.nextMaintenanceDate = nextMaintenanceDate;
        this.currentLoad = currentLoad;
        this.maxLoad = maxLoad;
        this.cargoList = cargoList;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
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

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public List<String> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<String> cargoList) {
        this.cargoList = cargoList;
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
        this.setHistoryRouteList(vehicle.getHistoryRouteList());
        this.setHistoryIncomeList(vehicle.getHistoryIncomeList());
        this.setToTalRevenue(vehicle.getToTalRevenue());
        this.setToTalCost(vehicle.getToTalCost());
        this.setToTalProfit(vehicle.getToTalProfit());
        this.setTimeStartList(vehicle.getTimeStartList());
        this.setTimeEndList(vehicle.getTimeEndList());
    }
}