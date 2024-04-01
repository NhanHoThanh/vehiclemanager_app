package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Passenger;
import project.api.drivers.services.PassengerService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    public PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Passenger>> getPassenger(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Passenger> driver = passengerService.getPassengerById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Passenger>>> getAllPassenger() {
        ResponseObject<List<Passenger>> driverList = passengerService.getAllPassenger();
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Passenger>>> getPassengerByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Passenger>> driverList = passengerService.getPassengerByAttributes(allParams);
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping

    public ResponseEntity<ResponseObject<Passenger>> createPassenger(@RequestBody Passenger driver) {
        ResponseObject<Passenger> responseObject = passengerService.createPassenger(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updatePassenger(@PathVariable String id,  @RequestBody Passenger driver) {
        ResponseObject responseObject = passengerService.updatePassenger(id, driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Passenger>> deletePassenger(@PathVariable String id) {
        ResponseObject<Passenger> responseObject = passengerService.deletePassenger(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}