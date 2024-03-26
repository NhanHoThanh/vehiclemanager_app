package project.api.drivers.models;


public class Passenger {
    private String IDPassenger;
    private String destination;
    private String departure;

    public Passenger(String IDPassenger, String destination, String departure, String name, int seatingPosition) {
        this.IDPassenger = IDPassenger;
        this.destination = destination;
        this.departure = departure;
        this.name = name;
        this.seatingPosition = seatingPosition;
    }

    public Passenger() {
    }

    public String getIDPassenger() {
        return IDPassenger;
    }

    public void setIDPassenger(String IDPassenger) {
        this.IDPassenger = IDPassenger;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatingPosition() {
        return seatingPosition;
    }

    public void setSeatingPosition(int seatingPosition) {
        this.seatingPosition = seatingPosition;
    }

    private String name;
    private int seatingPosition;
}

