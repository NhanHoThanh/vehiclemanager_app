package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Driver;
import project.api.drivers.repositories.DriverRepository;

import java.util.concurrent.ExecutionException;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver getDriverById(String id) throws ExecutionException, InterruptedException {
        return driverRepository.getDriverById(id);
    }
//    public List<Driver> getAllDrivers() throws ExecutionException, InterruptedException {
//        List<Driver> drivers = new ArrayList<>();
//        DataSnapshot dataSnapshot = databaseReference.get().get();
//        for (DataSnapshot driverSnapshot : dataSnapshot.getChildren()) {
//            Driver driver = driverSnapshot.getValue(Driver.class);
//            drivers.add(driver);
//        }
//        return drivers;
//    }
//    public ResponseObject getAllDrivers() {
//        ResponseObject<List<Driver>> responseObject = new ResponseObject<>();
//        try {
//            List<Driver> drivers = driverRepository.findAll();
//            if (drivers.isEmpty()) {
//                responseObject.setStatus("success");
//                responseObject.setMessage("No driver found");
//                return responseObject;
//            }
//            responseObject.setStatus("success");
//            responseObject.setMessage("Get all drivers successfully");
//            responseObject.setData(drivers);
//        } catch (Exception e) {
//            responseObject.setStatus("error");
//            responseObject.setMessage("An error occurred: " + e.getMessage());
//        }
//        return responseObject;
//    }

//    public ResponseObject getDriverById(Long id) {
//        ResponseObject<Driver> responseObject = new ResponseObject<>();
//        try {
//            Driver driver = driverRepository.findById(id).orElse(null);
//            if (driver != null) {
//                responseObject.setStatus("success");
//                responseObject.setMessage("Get driver by id successfully");
//                responseObject.setData(driver);
//            } else {
//                responseObject.setStatus("fail");
//                responseObject.setMessage("Driver not found");
//            }
//        } catch (Exception e) {
//            responseObject.setStatus("error");
//            responseObject.setMessage("An error occurred: " + e.getMessage());
//        }
//        return responseObject;
//    }

//    public ResponseObject createDriver(Driver driver) {
//        ResponseObject<Driver> responseObject = new ResponseObject<>();
//        try {
//            Driver newDriver = driverRepository.save(driver); // don't have to check for success as jpa will throw exception if failed
//            responseObject.setStatus("success");
//            responseObject.setMessage("Create driver successfully");
//            responseObject.setData(newDriver);
//        } catch (Exception e) {
//            responseObject.setStatus("error");
//            responseObject.setMessage("An error occurred: " + e.getMessage());
//        }
//        return responseObject;
//    }
//
//
//    public ResponseObject updateDriver(Long id, Driver driver) {
//        ResponseObject<Driver> responseObject = new ResponseObject<>();
//        try {
//            Driver driverUpdate = driverRepository.findById(id).orElse(null);
//            if (driverUpdate != null) {
//                BeanUtils.copyProperties(driverUpdate, driver);
//                driverRepository.save(driverUpdate);
//                responseObject.setStatus("success");
//                responseObject.setMessage("Update driver successfully");
//                responseObject.setData(driverUpdate);
//            } else {
//                responseObject.setStatus("error");
//                responseObject.setMessage("Driver not found");
//            }
//        } catch (Exception e) {
//            responseObject.setStatus("error");
//            responseObject.setMessage("An error occurred: " + e.getMessage());
//        }
//        return responseObject;
//    }
//    public ResponseObject deleteDriver(Long id) {
//        ResponseObject<Driver> responseObject = new ResponseObject<>();
//        try {
//            Driver driver = driverRepository.findById(id).orElse(null);
//            if (driver != null) {
//                driverRepository.delete(driver);
//                responseObject.setStatus("success");
//                responseObject.setMessage("Delete driver successfully");
//                responseObject.setData(driver);
//            } else {
//                responseObject.setStatus("fail");
//                responseObject.setMessage("Driver not found");
//            }
//        } catch (Exception e) {
//            responseObject.setStatus("error");
//            responseObject.setMessage("An error occurred: " + e.getMessage());
//        }
//        return responseObject;
//    }
}