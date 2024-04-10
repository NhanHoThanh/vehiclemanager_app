package project.api.drivers.models;

//use jakarta instead of javax
import jakarta.persistence.*;
import org.checkerframework.common.value.qual.StringVal;
import org.jetbrains.annotations.NotNull;

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

    private String routeId;

    private String status;

    private String vehicleId;
    private String vehicleType;
    private String email;
    public Driver() {
    }

    public Driver(String id, String name, String address, String phone_number, String license, String cccd, String routeId, String status, String vehicleId, String vehicleType, String email){
        this.id=id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.license = license;
        this.cccd = cccd;
        this.routeId = routeId;
        this.status = status;
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.email = email;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
