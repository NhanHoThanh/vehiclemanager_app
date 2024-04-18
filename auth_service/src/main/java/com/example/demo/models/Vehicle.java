// package com.example.demo.models;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;

// @Entity
// public class Vehicle {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;
//     private Long driverId;
//     private String licensePlate;
//     private String brand;
//     private String type;
//     private Float maxLoad;
//     private Integer numOfSeats;
    
//     public Vehicle() {};

//     public Vehicle(Long driverId, String licensePlate, String brand, String type, Float maxLoad, Integer numOfSeats) {
//         this.driverId = driverId;
//         this.licensePlate = licensePlate;
//         this.brand = brand;
//         this.type = type;
//         this.maxLoad = maxLoad;
//         this.numOfSeats = numOfSeats;
//     }

//     public Vehicle(Long driverId, String licensePlate, String brand, String type, Float maxLoad) {
//         this.driverId = driverId;
//         this.licensePlate = licensePlate;
//         this.brand = brand;
//         this.type = type;
//         this.maxLoad = maxLoad;
//         this.numOfSeats = 0;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Long getDriverId() {
//         return driverId;
//     }

//     public void setDriverId(Long driverId) {
//         this.driverId = driverId;
//     }

//     public String getLicensePlate() {
//         return licensePlate;
//     }

//     public void setLicensePlate(String licensePlate) {
//         this.licensePlate = licensePlate;
//     }

//     public String getBrand() {
//         return brand;
//     }

//     public void setBrand(String brand) {
//         this.brand = brand;
//     }

//     public String getType() {
//         return type;
//     }

//     public void setType(String type) {
//         this.type = type;
//     }

//     public Float getMaxLoad() {
//         return maxLoad;
//     }

//     public void setMaxLoad(Float maxLoad) {
//         this.maxLoad = maxLoad;
//     }

//     public Integer getNumOfSeats() {
//         return numOfSeats;
//     }

//     public void setNumOfSeats(Integer numOfSeats) {
//         this.numOfSeats = numOfSeats;
//     }
// }
