package project.api.drivers.models;
import java.util.Date;
import java.util.List;


public class Vehicle {
    private String idVehicle;
    private List<String> driverList;

    private List<String> historyRouteList;
    private List<String> historyIncomeList;
    private List<Date> timeStartList;
    private List<Date> timeEndList;
    private Double toTalRevenue;
    private Double toTalProfit;
    private Double toTalCost;
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


    public Vehicle(Double toTalRevenue, Double toTalProfit, Double toTalCost) {
        this.toTalRevenue = toTalRevenue;
        this.toTalProfit = toTalProfit;
        this.toTalCost = toTalCost;
    }

    public Vehicle(String idVehicle, List<String> driverList, List<String> historyRouteList, List<String> historyIncomeList, List<Date> timeStartList,
                   List<Date> timeEndList, Double toTalRevenue, Double toTalProfit, Double toTalCost, int capacity, String fuelType,
                   String status, String route, String vehicleType, Date timeStart, Date timeEnd, String destination, String departure) {
        this.idVehicle = idVehicle;
        this.driverList = driverList;
        this.historyRouteList = historyRouteList;
        this.historyIncomeList = historyIncomeList;
        this.timeStartList = timeStartList;
        this.timeEndList = timeEndList;
        this.toTalRevenue = toTalRevenue;
        this.toTalProfit = toTalProfit;
        this.toTalCost = toTalCost;
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

    public Vehicle(List<String> historyRouteList, List<String> historyIncomeList, List<Date> timeStartList, List<Date> timeEndList,
                   String route , Date timeStart, Date timeEnd  , String departure, String destination) {
        this.historyRouteList = historyRouteList;
        this.historyIncomeList = historyIncomeList;
        this.timeStartList = timeStartList;
        this.timeEndList = timeEndList;
        this.route=route;
        this.timeStart=timeStart;
        this.timeEnd=timeEnd;
        this.departure=departure;
        this.destination=destination;
    }

    public Vehicle(String idVehicle, List<String> driverList, List<String> hisRouteList, List<String> hisIncomeList, List<Date> timeStartList, List<Date> timeEndList, int capacity, String fuelType, String status, String route, String vehicleType, Date timeStart, Date timeEnd, String destination, String departure) {
        this.idVehicle = idVehicle;
        this.driverList = driverList;
        this.historyRouteList = hisRouteList;
        this.historyIncomeList = hisIncomeList;
        this.timeStartList = timeStartList;
        this.timeEndList = timeEndList;
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

    public Double getToTalRevenue() {
        return toTalRevenue;
    }

    public void setToTalRevenue(Double toTalRevenue) {
        this.toTalRevenue = toTalRevenue;
    }

    public Double getToTalProfit() {
        return toTalProfit;
    }

    public void setToTalProfit(Double toTalProfit) {
        this.toTalProfit = toTalProfit;
    }

    public Double getToTalCost() {
        return toTalCost;
    }

    public void setToTalCost(Double toTalCost) {
        this.toTalCost = toTalCost;
    }

    public List<String> getHistoryRouteList() {
        return historyRouteList;
    }

    public void setHistoryRouteList(List<String> historyRouteList) {
        this.historyRouteList = historyRouteList;
    }

    public List<String> getHistoryIncomeList() {
        return historyIncomeList;
    }

    public void setHistoryIncomeList(List<String> historyIncomeList) {
        this.historyIncomeList = historyIncomeList;
    }

    public List<Date> getTimeStartList() {
        return timeStartList;
    }

    public void setTimeStartList(List<Date> timeStartList) {
        this.timeStartList = timeStartList;
    }

    public List<Date> getTimeEndList() {
        return timeEndList;
    }

    public void setTimeEndList(List<Date> timeEndList) {
        this.timeEndList = timeEndList;
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