package project.api.drivers.models;


import java.util.List;

public class Cargo extends Vehicle{
    private String IDCargo;
    private int mass;
    private String receiver;
    private String sender;
    private String sendingPlace;
    private String receivingPlace;
    private String phoneNumber;
    private String nameCargo;

    public Cargo(String id, List<Driver> driverList, int capacity, String fuelType, String status, String route, String vehicleType, String IDCargo, int mass, String receiver, String sender, String sendingPlace, String receivingPlace, String phoneNumber, String nameCargo) {
        super(id, driverList, capacity, fuelType, status, route, vehicleType);
        this.IDCargo = IDCargo;
        this.mass = mass;
        this.receiver = receiver;
        this.sender = sender;
        this.sendingPlace = sendingPlace;
        this.receivingPlace = receivingPlace;
        this.phoneNumber = phoneNumber;
        this.nameCargo = nameCargo;
    }

    public Cargo() {
    }

    public String getIDCargo() {
        return IDCargo;
    }

    public void setIDCargo(String IDCargo) {
        this.IDCargo = IDCargo;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendingPlace() {
        return sendingPlace;
    }

    public void setSendingPlace(String sendingPlace) {
        this.sendingPlace = sendingPlace;
    }

    public String getreceivingPlace() {
        return receivingPlace;
    }

    public void setreceivingPlace(String receivingPlace) {
        this.receivingPlace = receivingPlace;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameCargo() {
        return nameCargo;
    }

    public void setNameCargo(String nameCargo) {
        this.nameCargo = nameCargo;
    }


}
