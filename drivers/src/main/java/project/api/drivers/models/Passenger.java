package project.api.drivers.models;


public class Passenger {
    private String idPassenger;
    private String destination;
    private String departure;
    private String name;
    private int age;
    private String seatingPosition;
    private double cosPassenger;

    public Passenger() {
    }

    public Passenger(String idPassenger, String destination, String departure, String name, int age, String seatingPosition, double cosPassenger) {
        this.idPassenger = idPassenger;
        this.destination = destination;
        this.departure = departure;
        this.name = name;
        this.age = age;
        this.seatingPosition = seatingPosition;
        this.cosPassenger = cosPassenger;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(String idPassenger) {
        this.idPassenger = idPassenger;
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

    public String getSeatingPosition() {
        return seatingPosition;
    }

    public void setSeatingPosition(String seatingPosition) {
        this.seatingPosition = seatingPosition;
    }

    public double getCosPassenger() {
        return cosPassenger;
    }

    public void setCosPassenger(double cosPassenger) {
        this.cosPassenger = cosPassenger;
    }
}

