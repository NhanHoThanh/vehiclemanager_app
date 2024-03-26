package project.api.drivers.models;


public class Cargo extends Vehicle{
    private String IDCargo;
    private int mass;

    public Cargo(String IDCargo, int mass, String receiver, String sender, String sendingPlace, String recipients, String phoneNumber, String nameCargo) {
        this.IDCargo = IDCargo;
        this.mass = mass;
        this.receiver = receiver;
        this.sender = sender;
        this.sendingPlace = sendingPlace;
        this.recipients = recipients;
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

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
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

    private String receiver;
    private String sender;
    private String sendingPlace;
    private String recipients;
    private String phoneNumber;
    private String nameCargo;
}
