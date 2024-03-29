package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.api.drivers.models.Cargo;
import project.api.drivers.services.CargoService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cargo")
public class CargoController {
    public CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getCargo(@PathVariable String id) throws ExecutionException, InterruptedException {
        Cargo cargo = cargoService.getCargoById(id);
        if (cargo != null) {
            return ResponseEntity.ok(cargo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
