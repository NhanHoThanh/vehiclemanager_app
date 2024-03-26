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
}
