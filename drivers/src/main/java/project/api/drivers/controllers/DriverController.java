package project.api.drivers.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.ultis.validation.CreateDriver;
import project.api.drivers.models.Driver;
import project.api.drivers.ultis.validation.UpdateDriver;
import project.api.drivers.ultis.validation.UpdateDriverRoute;
import project.api.drivers.ultis.validation.UpdateDriverVehicle;
import project.api.drivers.ultis.ResponseObject;
import project.api.drivers.services.DriverService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Driver>> getDriver(@PathVariable("id") String id) {
        if (!id.matches("\\d{14}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "ID must be exactly 14 digits", null));
        }
        ResponseObject<Driver> driver = driverService.getDriverById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Driver>>> getAllDrivers() {
        ResponseObject<List<Driver>> driverList = driverService.getAllDrivers();
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Driver>>> getDriversByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Driver>> driverList = driverService.getDriversByAttributes(allParams);
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject<Driver>> createDriver(@Valid  @RequestBody CreateDriver createDriver) {

        if(driverService.checkDriverValueExists("cccd", createDriver.getCccd())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "CCCD already exists", null));
        }
        if(driverService.checkDriverValueExists("phone_number", createDriver.getPhone_number())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "Phone number already exists", null));
        }
        if(driverService.checkDriverValueExists("email", createDriver.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "Email already exists", null));
        }
        Driver driver = ControllerUltis.getDriver(createDriver);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        final String id = LocalDateTime.now().format(formatter);
        driver.setId(id);

        DateTimeFormatter createdFormatter = DateTimeFormatter.ofPattern("HH-mm-ss dd-MM-yyyy");
        final String createdDate = LocalDateTime.now().format(createdFormatter);
        driver.setCreatedAt(createdDate);

        driver.setStatus("available");

        ResponseObject<Driver> responseObject = driverService.createDriver(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject<Driver>> updateDriver(@PathVariable("id")  String id, @RequestBody UpdateDriver updateDriver) {

        if (!id.matches("\\d{14}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "ID must be exactly 14 digits", null));
        }
        ResponseObject<Driver> checkObject = driverService.getDriverById(id);
        if (driverService.getDriverById(id).getStatus() == "fail") {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject<>("error", "ID does not exist", null));
        }
        if ("error".equals(checkObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(checkObject);
        }
        Driver driverToUpdate = (Driver) checkObject.getData();
        if(driverService.checkDriverValueExists("cccd", updateDriver.getCccd()) && !updateDriver.getCccd().equals(driverToUpdate.getCccd())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "CCCD already exists", null));
        }
        if(driverService.checkDriverValueExists("phone_number", updateDriver.getPhone_number()) && !updateDriver.getPhone_number().equals(driverToUpdate.getPhone_number())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "Phone number already exists", null));
        }
        if(driverService.checkDriverValueExists("email", updateDriver.getEmail()) && !updateDriver.getEmail().equals(driverToUpdate.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "Email already exists", null));
        }
        Driver driver = ControllerUltis.getDriver(updateDriver);

        ResponseObject<Driver> responseObject = driverService.updateDriver(id, driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}/vehicle")
    public ResponseEntity<ResponseObject<Driver>> updateVehicleV(@PathVariable("id") String id,@Valid @RequestBody UpdateDriverVehicle updateVehicle) {
        if (!id.matches("\\d{14}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "ID must be exactly 14 digits", null));
        }
        ResponseObject<Driver> findDriver = driverService.getDriverById(id);
        if ("success".equals(findDriver.getStatus())){
            Driver driver = new Driver();
            driver.setVehicleId(updateVehicle.getVehicleId());
                 driver.setVehicleType(updateVehicle.getVehicleType());
            ResponseObject<Driver> responseObject = driverService.updateDriver(id, driver);
            if ("error".equals(responseObject.getStatus())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
            }
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findDriver);
    }

    @PutMapping("/{id}/route")
    public ResponseEntity<ResponseObject<Driver>> updateRoute(@PathVariable("id") String id,@Valid @RequestBody UpdateDriverRoute updateDriverRoute) {
        if (!id.matches("\\d{14}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "ID must be exactly 14 digits", null));
        }
        ResponseObject<Driver> findDriver = driverService.getDriverById(id);
        if ("success".equals(findDriver.getStatus())){
            Driver driver = new Driver();
            driver.setRouteId(updateDriverRoute.getRouteId());
            ResponseObject<Driver> responseObject = driverService.updateDriver(id, driver);
            if ("error".equals(responseObject.getStatus())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
            }
            return ResponseEntity.ok(responseObject);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findDriver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Driver>> deleteDriver(@PathVariable("id") String id) {
        if (!id.matches("\\d{14}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject<>("error", "ID must be exactly 14 digits", null));
        }
        ResponseObject<Driver> responseObject = driverService.deleteDriver(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}