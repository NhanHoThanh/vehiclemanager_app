package project.api.drivers.models;

import jakarta.persistence.*;

import java.util.List;


public class Vehicle {
    private String Id;
    private List<Driver> driverList;
    private int dung_tich;
    private String fuelType;
    private String trang_thai;
    private String tuyen_duong;
    private String loai_xe;

}
