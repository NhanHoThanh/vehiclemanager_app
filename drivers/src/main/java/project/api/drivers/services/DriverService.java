package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Driver;
import project.api.drivers.repositories.DriverRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
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
                responseObject.setMessage("Internal server error");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }

    public boolean checkDriverValueExists(String attributeName, String attributeValue) {
        try {
            return driverRepository.checkDriverValueExists(attributeName, attributeValue);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByCccd(String cccd) {
        // Implement Firebase query to check if a Driver with the given cccd exists
        DriverRepository driverRepository = new DriverRepository();
        try {
            return driverRepository.checkDriverValueExists("cccd", cccd);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        // Implement Firebase query to check if a Driver with the given phoneNumber exists

        try {
            return driverRepository.checkDriverValueExists("phone_number", phoneNumber);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean existsByEmail(String email) {
        // Implement Firebase query to check if a Driver with the given email exists
        try {
            return driverRepository.checkDriverValueExists("email", email);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

}