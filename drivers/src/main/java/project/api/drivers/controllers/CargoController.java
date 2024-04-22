package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Cargo;
import project.api.drivers.services.CargoService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/api/cargo")
public class CargoController {
    public CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Cargo>> getCargo(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Cargo> cargo = cargoService.getCargoById(id);
        if (cargo != null) {
            return ResponseEntity.ok(cargo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Cargo>>> getAllCargo() {
        ResponseObject<List<Cargo>> cargoList = cargoService.getAllCargo();
        if (cargoList != null) {
            return ResponseEntity.ok(cargoList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Cargo>>> getCargoByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Cargo>> cargoList = cargoService.getCargoByAttributes(allParams);
        if (cargoList != null) {
            return ResponseEntity.ok(cargoList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject<Cargo>> createCargo(@RequestBody Cargo cargo) {
        ResponseObject<Cargo> responseObject = cargoService.createCargo(cargo);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCargo(@PathVariable String id,  @RequestBody Cargo cargo) {
        ResponseObject responseObject = cargoService.updateCargo(id, cargo);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Cargo>> deleteCargo(@PathVariable String id) {
        ResponseObject<Cargo> responseObject = cargoService.deleteCargo(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}