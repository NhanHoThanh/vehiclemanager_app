package project.api.drivers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.api.drivers.models.Coach;
import project.api.drivers.services.CoachService;

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
    public ResponseEntity<Coach> getCoach(@PathVariable String id) throws ExecutionException, InterruptedException {
        Coach coach = coachService.getCoachById(id);
        if (coach != null) {
            return ResponseEntity.ok(coach);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
