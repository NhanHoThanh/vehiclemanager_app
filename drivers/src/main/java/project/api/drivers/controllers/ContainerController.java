package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Cargo;
import project.api.drivers.models.Container;
import project.api.drivers.services.CargoService;
import project.api.drivers.services.ContainerService;
import project.api.drivers.ultis.ResponseObject;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/container")
public class ContainerController {
    public ContainerService containerService;
    public CargoService cargoService;

    public ContainerController(ContainerService containerService, CargoService cargoService) {
        this.containerService = containerService;
        this.cargoService = cargoService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Container>> getContainer(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Container> container = containerService.getContainerById(id);
        if (container != null) {
            return ResponseEntity.ok(container);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<java.util.List<Container>>> getAllContainer() {
        ResponseObject<java.util.List<Container>> containerList = containerService.getAllContainer();
        if (containerList != null) {
            return ResponseEntity.ok(containerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/cargo")
    public ResponseEntity<ResponseObject<List<Cargo>>> getAllCargo() {
        ResponseObject<List<Cargo>> cargoList = cargoService.getAllCargo();
        if (cargoList != null) {
            return ResponseEntity.ok(cargoList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/listCargo/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Cargo>>> getListCargo(@PathVariable String idVehicle) {
        ResponseObject<List<Cargo>> listCargo = containerService.getListCargo(idVehicle);
        if (listCargo != null) {
            return ResponseEntity.ok(listCargo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<java.util.List<Container>>> getContainerByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Container>> containerList = containerService.getContainerByAttributes(allParams);
        if (containerList != null) {
            return ResponseEntity.ok(containerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject<Container>> createContainer(@RequestBody Container container) {
        ResponseObject<Container> responseObject = containerService.createContainer(container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateContainer(@PathVariable String id,  @RequestBody Container container) {
        ResponseObject responseObject = containerService.updateContainer(id, container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Container>> deleteContainer(@PathVariable String id) {
        ResponseObject<Container> responseObject = containerService.deleteContainer(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/addCargo/{idVehicle}/{idCargo}")
    public ResponseEntity<ResponseObject> addCargo(@PathVariable String idVehicle, @PathVariable String idCargo,  @RequestBody Container container) {
        ResponseObject responseObject = containerService.addCargo(idVehicle, idCargo, container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @DeleteMapping("/removeCargo/{idVehicle}/{idCargo}")
    public ResponseEntity<ResponseObject> removeCargo(@PathVariable String idVehicle, @PathVariable String idCargo,  @RequestBody Container container) {
        ResponseObject responseObject = containerService.removeCargo(idVehicle, idCargo, container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}