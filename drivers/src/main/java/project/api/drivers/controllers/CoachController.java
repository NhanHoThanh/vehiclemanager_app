package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Coach;
import project.api.drivers.services.CoachService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/coach")
public class CoachController {
    public CoachService coachService;
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }
    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Coach>> getCoach(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Coach> driver = coachService.getCoachById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Coach>>> getAllCoach() {
        ResponseObject<List<Coach>> driverList = coachService.getAllCoach();
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Coach>>> getCoachByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Coach>> driverList = coachService.getCoachByAttributes(allParams);
        if (driverList != null) {
            return ResponseEntity.ok(driverList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping

    public ResponseEntity<ResponseObject<Coach>> createCoach(@RequestBody Coach driver) {
        ResponseObject<Coach> responseObject = coachService.createCoach(driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateCoach(@PathVariable String id,  @RequestBody Coach driver) {
        ResponseObject responseObject = coachService.updateCoach(id, driver);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Coach>> deleteCoach(@PathVariable String id) {
        ResponseObject<Coach> responseObject = coachService.deleteCoach(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
}