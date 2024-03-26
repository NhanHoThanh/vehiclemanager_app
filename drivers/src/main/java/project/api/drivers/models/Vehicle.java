package project.api.drivers.models;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String Id;
    private List<Driver> driverList;
    private int capacity;
    private String fuelType;
    private String status;
    private String route;
    private String vehicleType;

}
