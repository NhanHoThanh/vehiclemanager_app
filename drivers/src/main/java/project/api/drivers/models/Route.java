package project.api.drivers.models;

import java.util.Date;
import java.util.List;

public class Route {
    private int idRoute;
    private String departure;
    private String destination;
    private List<String> station;
    private double distance;
    private double duration;

    private Income income;

    public Route(){}

    public Route(int idRoute, String destination, String departure, List<String> station,
                 double distance, double duration, Income income) {
        this.idRoute=idRoute;
        this.destination = destination;
        this.departure = departure;
        this.station = station;
        this.distance = distance;
        this.duration = duration;
        this.income= income;
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

    public List<String> getStation() {
        return station;
    }

    public void setStation(List<String> station) {
        this.station = station;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }
}
