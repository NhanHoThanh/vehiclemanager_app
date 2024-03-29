package project.api.drivers.models;


import java.util.List;

public class Cargo{
    private String idCargo;
    private double mass;
    private String receiver;
    private String sender;
    private String sendingPlace;
    private String receivingPlace;
    private String phoneNumber;
    private String nameCargo;
    private double size;
    private double costCargo;

    public Cargo() {
    }

    public Cargo(String idCargo, double mass, String receiver, String sender, String sendingPlace, String receivingPlace, String phoneNumber, String nameCargo, double size, double costCargo) {
        this.idCargo = idCargo;
        this.mass = mass;
        this.receiver = receiver;
        this.sender = sender;
        this.sendingPlace = sendingPlace;
        this.receivingPlace = receivingPlace;
        this.phoneNumber = phoneNumber;
        this.nameCargo = nameCargo;
        this.size = size;
        this.costCargo = costCargo;
    }

    public String getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo = idCargo;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
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

    public String getReceivingPlace() {
        return receivingPlace;
    }

    public void setReceivingPlace(String receivingPlace) {
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getCostCargo() {
        return costCargo;
    }

    public void setCostCargo(double costCargo) {
        this.costCargo = costCargo;
    }
}
