package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.api.drivers.models.Container;
import project.api.drivers.services.ContainerService;

import java.awt.*;
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
    public ResponseEntity<Container> getContainer(@PathVariable String id) throws ExecutionException, InterruptedException {
        Container container = containerService.getContainerById(id);
        if (container != null) {
            return ResponseEntity.ok(container);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
