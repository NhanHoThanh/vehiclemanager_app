package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Driver;
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
        ResponseObject<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Vehicle>>> getAllVehicle() {
        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getAllVehicle();
        if (vehicleList != null) {
            return ResponseEntity.ok(vehicleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/listDriver/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Driver>>> getListDriver(@PathVariable String idVehicle) {
        ResponseObject<List<Driver>> listDriver = vehicleService.getListDriver(idVehicle);
        if (listDriver != null) {
            return ResponseEntity.ok(listDriver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Vehicle>>> getVehicleByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getVehicleByAttributes(allParams);
        if (vehicleList != null) {
            return ResponseEntity.ok(vehicleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/searchroute")
    public ResponseEntity<ResponseObject<List<Vehicle>>> getVehicleRoute(@RequestParam Map<String, String> departure, @RequestParam Map<String, String> destination) {
        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getVehicleRoute(departure, destination);
        System.out.println("controller");
        if (vehicleList != null) {
            return ResponseEntity.ok(vehicleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping
    public ResponseEntity<ResponseObject<Vehicle>> createVehicle(@RequestBody Vehicle vehicle) {
        ResponseObject<Vehicle> responseObject = vehicleService.createVehicle(vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateVehicle(@PathVariable String id,  @RequestBody Vehicle vehicle) {
        ResponseObject responseObject = vehicleService.updateVehicle(id, vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/addDriver/{idVehicle}/{idDriver}")
    public ResponseEntity<ResponseObject> addDriver(@PathVariable String idVehicle, @PathVariable String idDriver,  @RequestBody Vehicle vehicle) {
        ResponseObject responseObject = vehicleService.addDriver(idVehicle, idDriver,  vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @DeleteMapping("/removeDriver/{idVehicle}/{idDriver}")
    public ResponseEntity<ResponseObject> removeDriver(@PathVariable String idVehicle, @PathVariable String idDriver,  @RequestBody Vehicle vehicle) {
        ResponseObject responseObject = vehicleService.removeDriver(idVehicle, idDriver,  vehicle);
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