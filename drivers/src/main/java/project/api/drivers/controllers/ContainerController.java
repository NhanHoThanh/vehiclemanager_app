package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Container;
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

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }
    @GetMapping("/test")
    public String test() {
        return "Test";
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Container>> getContainer(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Container> driver = containerService.getContainerById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<java.util.List<Container>>> getAllContainer() {
        ResponseObject<java.util.List<Container>> driverList = containerService.getAllContainer();
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<java.util.List<Container>>> getContainerByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Container>> driverList = containerService.getContainerByAttributes(allParams);
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping

    public ResponseEntity<ResponseObject<Container>> createContainer(@RequestBody Container driver) {
        ResponseObject<Container> responseObject = containerService.createContainer(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateContainer(@PathVariable String id,  @RequestBody Container driver) {
        ResponseObject responseObject = containerService.updateContainer(id, driver);
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
}