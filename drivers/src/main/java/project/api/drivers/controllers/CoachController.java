package project.api.drivers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.api.drivers.services.CoachService;

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

}
