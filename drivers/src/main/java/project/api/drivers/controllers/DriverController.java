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
        ResponseObject<Driver> responseObject = driverService.createDriver(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateDriver(@PathVariable String id,  @RequestBody Driver driver) {
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