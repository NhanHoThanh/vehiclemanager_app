package project.api.drivers.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Driver;
import project.api.drivers.services.DriverService;

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
    public ResponseEntity<Driver> getDriver(@PathVariable String id) throws ExecutionException, InterruptedException {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
//    @GetMapping
//    public ResponseEntity<ResponseObject> getAllDrivers() {
//        return ResponseEntity.ok(driverService.getAllDrivers());
//    }

//    @PostMapping
//    public ResponseEntity<ResponseObject> createDriver(@Valid @RequestBody Driver driver) {
//        ResponseObject responseObject = driverService.createDriver(driver);
//        if ("error".equals(responseObject.getStatus())) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
//        }
//        return ResponseEntity.ok(responseObject);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseObject> updateDriver(@PathVariable Long id, @Valid @RequestBody Driver driver) {
//        ResponseObject responseObject = driverService.updateDriver(id, driver);
//        if ("error".equals(responseObject.getStatus())) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
//        }
//        return ResponseEntity.ok(responseObject);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseObject> deleteDriver(@PathVariable Long id) {
//        ResponseObject responseObject = driverService.deleteDriver(id);
//        if ("error".equals(responseObject.getStatus())) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
//        }
//        return ResponseEntity.ok(responseObject);
//    }
}