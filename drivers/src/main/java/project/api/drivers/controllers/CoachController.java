package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.*;
import project.api.drivers.services.CoachService;
import project.api.drivers.services.PassengerService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/api/coach")
public class CoachController {
    @Autowired
    private RestTemplate restTemplate;
    public CoachService coachService;
    public PassengerService passengerService;

    public CoachController(CoachService coachService, PassengerService passengerService) {
        this.coachService = coachService;
        this.passengerService = passengerService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Coach>> getCoach(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Coach> coach = coachService.getCoachById(id);
        System.out.println("controller");
        if (coach != null) {
            return ResponseEntity.ok(coach);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Coach>>> getAllCoach() {
        ResponseObject<List<Coach>> coachList = coachService.getAllCoach();
        if (coachList != null) {
            return ResponseEntity.ok(coachList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/listPassenger/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Passenger>>> getListPassenger(@PathVariable String idVehicle) {
        ResponseObject<List<Passenger>> listPassenger = coachService.getListPassenger(idVehicle);
        if (listPassenger != null) {
            return ResponseEntity.ok(listPassenger);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Coach>>> getCoachByAttributes(@RequestBody Coach coach) {
        ResponseObject<List<Coach>> coachList = coachService.getCoachByAttributes(coach);
        if (coachList != null) {
            return ResponseEntity.ok(coachList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
//    @GetMapping("/search")
//    public ResponseEntity<ResponseObject<List<Coach>>> getCoachByAttributes(@RequestParam Map<String, String> allParams) {
//        ResponseObject<List<Coach>> coachList = coachService.getCoachByAttributes(allParams);
//        if (coachList != null) {
//            return ResponseEntity.ok(coachList);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }


    @PostMapping
    public ResponseEntity<ResponseObject<Coach>> createCoach(@RequestBody Coach coachVehicle) {
        ResponseObject<Coach> responseObject = coachService.createCoach(coachVehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    //validate: check for duplicate in field, check for valid input, check for required field, unknown field and such. (ongoing)
    public ResponseEntity<ResponseObject> updateCoach(@PathVariable String id,  @RequestBody Coach coach) {
        ResponseObject responseObject = coachService.updateCoach(id, coach);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/addPassenger/{idVehicle}/{idPassenger}")
    public ResponseEntity<ResponseObject> addPassenger(@PathVariable String idVehicle, @PathVariable String idPassenger) {
        ResponseEntity<ResponseObject<Passenger>> responseEntityPassenger = restTemplate.exchange(
                "/api/passenger/{idPassenger}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Passenger>>() {
                },
                idPassenger
        );
        ResponseObject<Passenger>  responsePassenger =  responseEntityPassenger.getBody();
        Passenger passenger =  (Passenger)responsePassenger.getData();
        String routeDeparture = passenger.getDeparture();
        String routeDestination = passenger.getDestination();
        String url = "/api/route/checkRoute/" + idVehicle + "?departure=" + routeDeparture + "&destination=" + routeDestination;
        System.out.println(url);
//        System.out.println("http://localhost:8080/api/route/checkRoute/Coach7?departure=Ho Chi Minh&destination=Dak Lak");
        ResponseEntity<ResponseObject<Boolean>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Boolean>>() {},
                idVehicle


        );
        System.out.println("alo");
        ResponseObject<Boolean> responseBoolean = response.getBody();
        Boolean BooleanData = (Boolean) responseBoolean.getData();
//        Boolean responseRoute =  responseEntityBoolean.getBody();
        System.out.println(BooleanData);

        ResponseObject responseObject = coachService.addPassenger(idVehicle, idPassenger, BooleanData);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @DeleteMapping("/removePassenger/{idVehicle}/{idPassenger}")
    public ResponseEntity<ResponseObject> removePassenger(@PathVariable String idVehicle, @PathVariable String idPassenger) {
        ResponseObject responseObject = coachService.removePassenger(idVehicle, idPassenger);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    //delete all passenger
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Coach>> deleteCoach(@PathVariable String id) {
        ResponseObject<Coach> responseObject = coachService.deleteCoach(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }


}