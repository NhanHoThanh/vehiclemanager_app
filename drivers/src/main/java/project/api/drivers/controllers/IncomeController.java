package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.api.drivers.models.Coach;
import project.api.drivers.models.Income;
import project.api.drivers.models.Passenger;
import project.api.drivers.models.Route;
import project.api.drivers.services.IncomeService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/income")


public class IncomeController {


//    @Autowired
//    public IncomeController(Income2Service incomeService) {
//        this.incomeService = incomeService;
//    }
//
//    @GetMapping("/{id}")
//    public Income getIncomeById(@PathVariable String id) throws ExecutionException, InterruptedException {
//        return incomeService.getIncomeById(id);
//    }
//
//    @GetMapping("/all")
//    public List<Income> getAllIncome() throws ExecutionException, InterruptedException {
//        return incomeService.getAllIncome();
//    }
//
//    @PostMapping("/create")
//    public void createIncome(@RequestBody Income income) throws ExecutionException, InterruptedException {
//        incomeService.updateIncome(income);
//    }
//
//    @PutMapping("/update")
//    public void updateIncome(@RequestBody Income income) throws ExecutionException, InterruptedException {
//        incomeService.updateIncome(income);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteIncome(@PathVariable String id) throws ExecutionException, InterruptedException {
//        incomeService.deleteIncome(id);
//    }
   @Autowired
   private IncomeService incomeService;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Income>> getIncome(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Income> income = incomeService.getIncomeById(id);
        if (income != null) {
            return ResponseEntity.ok(income);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Income>>> getAllIncome() {
        ResponseObject<List<Income>> incomeList = incomeService.getAllIncome();
        if (incomeList != null) {
            return ResponseEntity.ok(incomeList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping
    public ResponseEntity<ResponseObject<Income>> createIncome(@RequestBody Income income) {
        ResponseObject<Income> responseObject = incomeService.createIncome(income);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateIncome(@PathVariable String id,  @RequestBody Income income) {
        ResponseObject responseObject = incomeService.updateIncome(id,income);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Income>> deleteIncome(@PathVariable String id) {
        ResponseObject<Income> responseObject = incomeService.deleteIncome(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @GetMapping("/{idVehicle}")
    public ResponseEntity<ResponseObject<Income>> calculateIncomeFormSpecificCoach(@PathVariable String idVehicle) throws ExecutionException, InterruptedException {
        ResponseEntity<Passenger[]> responseEntity = restTemplate.getForEntity("/api/coach/listPassenger/{idVehicle}",Passenger[].class);
//        ResponseEntity<ResponseObject<Coach>> coach = restTemplate.getForEntity("/api/coach/{idVehicle}",ResponseObject.class,idVehicle);
//        ResponseEntity<ResponseObject> coachResponseEntity = restTemplate.getForEntity("/api/coach/{idVehicle}", ResponseObject.class);
//        ResponseEntity<ResponseObject<Coach>> coachResponseEntity = restTemplate.getForEntity("/api/coach/{idVehicle}", ResponseObject.class, idVehicle);
        ResponseEntity<ResponseObject<Coach>> coachResponseEntity = restTemplate.exchange(
                "/api/coach/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Coach>>() {},
                idVehicle
        );

        ResponseObject<Coach> coachResponse = coachResponseEntity.getBody();
        assert coachResponse != null;
        Coach coach = (Coach) coachResponse.getData();
        String idRoute = coach.getRoute();
        ResponseEntity<ResponseObject<Route>> routeResponseEntity = restTemplate.exchange(
                "/api/route/{idRoute}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Route>>() {},
                idVehicle
        );
        ResponseObject<Route> routeResponse =routeResponseEntity.getBody();
        assert routeResponse != null;
        Route route= (Route) routeResponse.getData();


        ResponseObject<Income> income = incomeService.calculateIncomeFromSpecificCoach(responseEntity,coach,route );
        if (income != null) {
            return ResponseEntity.ok(income);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
