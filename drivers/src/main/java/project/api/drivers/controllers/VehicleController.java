package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Vehicle;
import project.api.drivers.services.VehicleService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    public VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Vehicle>> getVehicle(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Vehicle> driver = vehicleService.getVehicleById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Vehicle>>> getAllVehicle() {
        ResponseObject<List<Vehicle>> driverList = vehicleService.getAllVehicle();
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Vehicle>>> getVehicleByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Vehicle>> driverList = vehicleService.getVehicleByAttributes(allParams);
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping

    public ResponseEntity<ResponseObject<Vehicle>> createVehicle(@RequestBody Vehicle driver) {
        ResponseObject<Vehicle> responseObject = vehicleService.createVehicle(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateVehicle(@PathVariable String id,  @RequestBody Vehicle driver) {
        ResponseObject responseObject = vehicleService.updateVehicle(id, driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Vehicle>> deleteVehicle(@PathVariable String id) {
        ResponseObject<Vehicle> responseObject = vehicleService.deleteVehicle(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}