package project.api.drivers.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Driver;
import project.api.drivers.ultis.ResponseObject;
import project.api.drivers.repositories.DriverRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

//    public Driver getDriverById(String id) throws ExecutionException, InterruptedException {
//        return driverRepository.getDriverById(id);
//    }
//    public List<Driver> getAllDrivers() throws ExecutionException, InterruptedException {
//        List<Driver> drivers = new ArrayList<>();
//        DataSnapshot dataSnapshot = databaseReference.get().get();
//        for (DataSnapshot driverSnapshot : dataSnapshot.getChildren()) {
//            Driver driver = driverSnapshot.getValue(Driver.class);
//            drivers.add(driver);
//        }
//        return drivers;
//    }
    public ResponseObject<List<Driver>> getAllDrivers() {
        ResponseObject<List<Driver>> responseObject = new ResponseObject<>();
        try {
            List<Driver> drivers = driverRepository.getAllDocuments("Drivers", Driver.class);
            if (drivers.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No driver found");
                return responseObject;
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all drivers successfully");
            responseObject.setData(drivers);
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject<Driver> getDriverById(String id) {
        ResponseObject<Driver> responseObject = new ResponseObject<>();
        try {
            Driver driver = driverRepository.getDriverById(id);
            if (driver != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get driver by id successfully");
                responseObject.setData(driver);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Driver not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }

public ResponseObject<Driver> createDriver(Driver driver) {
    ResponseObject<Driver> responseObject = new ResponseObject<>();
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        final String id = LocalDateTime.now().format(formatter);
        driver.setId(id);

        DateTimeFormatter createdFormatter = DateTimeFormatter.ofPattern("HH-mm-ss dd-MM-yyyy");
        final String createdDate = LocalDateTime.now().format(createdFormatter);
        driver.setCreatedAt(createdDate);

        final Driver newDriver = driverRepository.createDriver(driver);
        responseObject.setStatus("success");
        responseObject.setMessage("Create driver successfully");
        responseObject.setData(newDriver);
    } catch (Exception e) {
        responseObject.setStatus("error");
        responseObject.setMessage("An error occurred: " + e.getMessage());
    }
    return responseObject;
}
//
//
    public ResponseObject<Driver> updateDriver(String id, Driver driver) {
        ResponseObject<Driver> responseObject = new ResponseObject<>();
        try {
            Driver driverUpdate = driverRepository.getDriverById(id);
            if (driverUpdate != null) {
                // BeanUtils.copyProperties(driverUpdate, driver);
                driverRepository.updateDriver(id, driver);
                responseObject.setStatus("success");
                responseObject.setMessage("Update driver successfully");
                responseObject.setData(driverUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Driver not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }
    public ResponseObject<Driver> deleteDriver(String id) {
        ResponseObject<Driver> responseObject = new ResponseObject<>();
        try {
            Driver driver = driverRepository.getDriverById(id);
            if (driver != null) {
                driverRepository.deleteDriverById(driver.getId());
                responseObject.setStatus("success");
                responseObject.setMessage("Delete driver successfully");
                responseObject.setData(driver);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Driver not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }

    public ResponseObject<List<Driver>> getDriversByAttributes(Map<String, String> allParams) {
        ResponseObject<List<Driver>> responseObject = new ResponseObject<>();
        try {
            List<Driver> drivers = driverRepository.getDocumentsByMultipleAttributes("Drivers", allParams, Driver.class);
            if (drivers != null && !drivers.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get drivers successfully");
                responseObject.setData(drivers);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Loi o day");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }
}