package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Coach;
import project.api.drivers.models.Passenger;
import project.api.drivers.services.CargoService;
import project.api.drivers.services.PassengerService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/api/passenger")
public class PassengerController {
    public PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Passenger>> getPassenger(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Passenger> container = passengerService.getPassengerById(id);
        if (container != null) {
            return ResponseEntity.ok(container);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Passenger>>> getAllPassenger() {
        ResponseObject<List<Passenger>> containerList = passengerService.getAllPassenger();
        if (containerList != null) {
            return ResponseEntity.ok(containerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Passenger>>> getPassengerByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Passenger>> containerList = passengerService.getPassengerByAttributes(allParams);
        if (containerList != null) {
            return ResponseEntity.ok(containerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject<Passenger>> createPassenger(@RequestBody Passenger container) {
        ResponseObject<Passenger> responseObject = passengerService.createPassenger(container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updatePassenger(@PathVariable String id,  @RequestBody Passenger container) {
        ResponseObject responseObject = passengerService.updatePassenger(id, container);
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
    @PutMapping("registerVehicle/{idVehicle}/{idPassenger}")
    public ResponseEntity<ResponseObject> registerVehicle(@PathVariable String idVehicle, @PathVariable String idPassenger ) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<Passenger>> passengerResponseEntity =  getPassenger(idPassenger);
        ResponseObject<Passenger> passengerResponseObject = passengerResponseEntity.getBody();
        Passenger passenger = (Passenger) passengerResponseObject.getData();
        List<String> listVehicle = passenger.getListVehicle();
        listVehicle.add(idVehicle);
        passenger.setListVehicle(listVehicle);
        return updatePassenger(idPassenger,passenger);

    }
}