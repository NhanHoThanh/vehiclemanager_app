package project.api.drivers.models;

//use jakarta instead of javax
import jakarta.persistence.*;
import project.api.drivers.models.Vehicle;
import java.util.List;
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String address;
    private String phone_number;
    private String license;
    private String cccd;
    private String route;

    private String status;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Vehicle vehicle;

    public Driver() {
    }

    public Driver(String id,String name, String address, String phone_number, String license, String cccd, String route, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.license = license;
        this.cccd = cccd;
        this.route = route;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCCCD() {
        return cccd;
    }

    public void setCCCD(String cccd) {
        this.cccd = cccd;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
}
