// package com.example.demo.models;

// import java.util.List;

// import com.example.demo.models.Vehicle;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;

// @Entity
// public class Driver {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;
//     private String fullname;
//     private String dateOfBirth;
//     private String cccd;
//     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Vehicle> ownVehicles;
    
//     public Driver() {
//     }

//     public Driver(Long id, String fullname, String dateOfBirth, String cccd) {
//         this.id = id;
//         this.fullname = fullname;
//         this.dateOfBirth = dateOfBirth;
//         this.cccd = cccd;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getFullname() {
//         return fullname;
//     }

//     public void setFullname(String fullname) {
//         this.fullname = fullname;
//     }

//     public String getDateOfBirth() {
//         return dateOfBirth;
//     }

//     public void setDateOfBirth(String dateOfBirth) {
//         this.dateOfBirth = dateOfBirth;
//     }

//     public String getCccd() {
//         return cccd;
//     }

//     public void setCccd(String cccd) {
//         this.cccd = cccd;
//     }

//     public List<Vehicle> getOwnVehicles() {
//         return ownVehicles;
//     }

//     public void setOwnVehicles(List<Vehicle> ownVehicles) {
//         this.ownVehicles = ownVehicles;
//     }
// }
