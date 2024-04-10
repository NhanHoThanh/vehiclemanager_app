package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Driver;
import project.api.drivers.ultis.ResponseObject;
import project.api.drivers.services.DriverService;

import javax.tools.Diagnostic;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import project.api.drivers.ultis.validation.DriverValidation;
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
    public ResponseEntity<ResponseObject<Driver>> getDriver(@PathVariable String id) throws ExecutionException, InterruptedException {
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

    public ResponseEntity<ResponseObject<Driver>> createDriver(@RequestBody Driver driver) {

    //validate: check for duplicate in field, check for valid input, check for required field, unknown field and such. (ongoing)
        DriverValidation validation = new DriverValidation();
        String validationErrors = validation.createValidation(driver);
        if (validationErrors != null) {
            // Handle validation errors
            ResponseObject<Driver> responseObject = new ResponseObject<>();
            responseObject.setStatus("error");
            responseObject.setMessage(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);

        }
        ResponseObject<Driver> responseObject = driverService.createDriver(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    //validate: check for duplicate in field, check for valid input, check for required field, unknown field and such. (ongoing)
    //validation

    public ResponseEntity<ResponseObject> updateDriver(@PathVariable String id,  @RequestBody Driver driver) {
        DriverValidation validation = new DriverValidation();
        String validationErrors = validation.updateValidation(driver);
        if (validationErrors != null) {
            // Handle validation errors
            ResponseObject<Driver> responseObject = new ResponseObject<>();
            responseObject.setStatus("error");
            responseObject.setMessage(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
        ResponseObject responseObject = driverService.updateDriver(id, driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Driver>> deleteDriver(@PathVariable String id) {
        ResponseObject<Driver> responseObject = driverService.deleteDriver(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}