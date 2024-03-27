package project.api.drivers.models;
import java.util.List;
import java.util.Date;


public class Container extends Vehicle{
    private String cargoType;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private int currentLoad;
    private int maxLoad;
    private List<Cargo> cargoList;

    public Container() {
    }

    public Container(String cargoType, Date previousMaintenanceDate, Date nextMaintenanceDate, int currentLoad, int maxLoad, List<Cargo> cargoList) {
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

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }
}