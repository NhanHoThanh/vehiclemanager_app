package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.api.drivers.models.*;
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
    @GetMapping("/getIncome/test/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Passenger>>> calculateIncomeFormSpecificCoachTestMethod(@PathVariable String idVehicle) throws ExecutionException, InterruptedException {
//        ResponseEntity<ResponseObject<List<Passenger>>>responseEntity = restTemplate.getForEntity("/api/coach/listPassenger/{idVehicle}",Passenger[].class);
//        ResponseEntity<Coach> coach3 = restTemplate.getForEntity("/api/coach/{idVehicle}",Coach.class,idVehicle);
//        ResponseEntity<ResponseObject> coachResponseEntity = restTemplate.getForEntity("/api/coach/{idVehicle}", ResponseObject.class);

        //
        ResponseEntity<ResponseObject<List<Passenger>>> responseEntity = restTemplate.exchange(
                "/api/coach/listPassenger/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<List<Passenger>>>() {
                },
                idVehicle
        );
        //
//        ResponseEntity<ResponseObject> coachResponseEntity1 = restTemplate.getForEntity("/api/coach/{idVehicle}", ResponseObject.class,idVehicle);
//        ResponseObject coachResponse1 = coachResponseEntity1.getBody();
//        assert coachResponse1 != null;
//        Coach coach1 = (Coach) coachResponse1.getData();



//        ResponseEntity<ResponseObject<Coach>> coachResponseEntity = restTemplate.exchange(
//                "/api/coach/{idVehicle}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<ResponseObject<Coach>>() {},
//                idVehicle
//        );
//
//        ResponseObject<Coach> coachResponse = coachResponseEntity.getBody();
//        assert coachResponse != null;
//        Coach coach = (Coach) coachResponse.getData();



//        String idRoute = coach.getRoute();
//        ResponseEntity<ResponseObject<Route>> routeResponseEntity = restTemplate.exchange(
//                "/api/route/{idRoute}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<ResponseObject<Route>>() {},
//                idVehicle
//        );
//        ResponseObject<Route> routeResponse =routeResponseEntity.getBody();
//        assert routeResponse != null;
//        Route route= (Route) routeResponse.getData();


//        ResponseObject<Income> income = incomeService.calculateIncomeFromSpecificCoach(responseEntity,coach1);
//        ResponseObject<Coach> coach2 = new ResponseObject<>();
//        coach2.setData(coach1);
//        if (coach2 != null) {
//            return ResponseEntity.ok(coach2);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        return responseEntity ;
    }
@GetMapping("/getIncome/{idVehicle}")
public ResponseEntity<ResponseObject<Coach>> calculateIncomeFromSpecificCoach(@PathVariable String idVehicle) {
    try {
        // Gọi API để lấy thông tin Coach
        ResponseEntity<ResponseObject<Coach>> responseEntity = restTemplate.exchange(
                "/api/coach/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Coach>>() {
                },
                idVehicle
        );

        // Kiểm tra nếu có dữ liệu và trả về kết quả
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            ResponseObject<Coach> responseObject = responseEntity.getBody();
            if (responseObject.getData() != null) {
                return ResponseEntity.ok(responseObject);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).build();
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu có
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject<>(null, "An error occurred: " + e.getMessage(), "error"));
//    }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject<Coach>(null, "An error occurred: " + e.getMessage(), null));

    }
   }

    @GetMapping("/getIncome/route/{idVehicle}")
    public ResponseEntity<ResponseObject<Route>> getRouteFormIncome (@PathVariable String idVehicle )  {
        ResponseEntity<ResponseObject<Coach>> responseEntity = restTemplate.exchange(
                "/api/coach/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Coach>>() {
                },
                idVehicle
        );
        ResponseObject<Coach> coachResponseObject = responseEntity.getBody();
        Coach coach = (Coach) coachResponseObject.getData();
        String  route = coach.getRoute();
        ResponseEntity<ResponseObject<Route>> responseRoute = restTemplate.exchange(
                "/api/route/{route}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Route>>() {
                },
               route
        );
        return responseRoute;
    }

    @GetMapping("/getIncomeCoach/{idVehicle}")
    public ResponseEntity<ResponseObject<Income>> calculateIncomeFormCoach (@PathVariable String idVehicle  ) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<List<Passenger>>> responseEntityPassenger = restTemplate.exchange(
                "/api/coach/listPassenger/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<List<Passenger>>>() {
                },
                idVehicle
        );
        ResponseObject<List<Passenger>> responsePassenger = responseEntityPassenger.getBody();
        List<Passenger> passengerList = (List<Passenger>) responsePassenger.getData();
        // Chuyển List<Passenger> thành Passenger[]
        Passenger[] passengerArray = passengerList.toArray(new Passenger[0]);
        ResponseEntity<ResponseObject<Coach>> responseEntityCoach = restTemplate.exchange(
                "/api/coach/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Coach>>() {
                },
                idVehicle
        );
        ResponseObject<Coach> coachResponseObject = responseEntityCoach.getBody();
        Coach coach = (Coach) coachResponseObject.getData();
        String  route = coach.getRoute();
        ResponseEntity<ResponseObject<Route>> responseRoute = restTemplate.exchange(
                "/api/route/{route}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Route>>() {
                },
                route
        );
        //
        ResponseObject<Route> responseRouteEntity = responseRoute.getBody();
        assert responseRouteEntity != null;
        Route routeData = (Route) responseRouteEntity.getData();


        //
        ResponseObject<Income> incomeResponse  = incomeService.calculateIncomeFromSpecificCoach( passengerArray ,routeData);

        if ( incomeResponse  != null) {
            return ResponseEntity.ok( incomeResponse );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
    @GetMapping("/getIncomeContainer/{idVehicle}")
    public ResponseEntity<ResponseObject<Income>> calculateIncomeFormContainer (@PathVariable String idVehicle  ) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<List<Cargo>>> responseEntityPassenger = restTemplate.exchange(
                "/api/container/listCargo/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<List<Cargo>>>() {
                },
                idVehicle
        );
        ResponseObject<List<Cargo>> responseCargo = responseEntityPassenger.getBody();
        List<Cargo> cargoList = (List<Cargo>) responseCargo.getData();
        // Chuyển List<Passenger> thành Passenger[]
        Cargo[] cargoArray = cargoList.toArray(new Cargo[0]);
        ResponseEntity<ResponseObject<Container>> responseEntityCoach = restTemplate.exchange(
                "/api/container/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Container>>() {
                },
                idVehicle
        );
        ResponseObject<Container> coachResponseObject = responseEntityCoach.getBody();
        Container container = (Container) coachResponseObject.getData();
        String  route = container.getRoute();
        ResponseEntity<ResponseObject<Route>> responseRoute = restTemplate.exchange(
                "/api/route/{route}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Route>>() {
                },
                route
        );
        //
        ResponseObject<Route> responseRouteEntity = responseRoute.getBody();
        assert responseRouteEntity != null;
        Route routeData = (Route) responseRouteEntity.getData();


        //
        ResponseObject<Income> incomeResponse  = incomeService.calculateIncomeFromSpecificContainer(cargoArray, routeData);

        if ( incomeResponse  != null) {
            return ResponseEntity.ok( incomeResponse );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }
//      @GetMapping("getTotalIncome/{idVehicle}")
//      Double getTotalIncome (@PathVariable String idVehicle ) throws ExecutionException, InterruptedException {
//          ResponseEntity<ResponseObject<Vehicle>> responseEntityCoach = restTemplate.exchange(
//                  "/api/coach/{idVehicle}",
//                  HttpMethod.GET,
//                  null,
//                  new ParameterizedTypeReference<ResponseObject<Vehicle>>() {
//                  },
//                  idVehicle
//          );
//          ResponseObject<Vehicle> coachResponseObject = responseEntityCoach.getBody();
//          Vehicle vehicle = (Vehicle) coachResponseObject.getData();
//          List<String> listIdRoute = vehicle;
//          Double totalRenevue=0.0;
//           for(String id: listIdRoute ){
//               ResponseEntity<ResponseObject<Income>> responseIncome = getIncome(id);
//               ResponseObject<Income> responseRouteEntity = responseIncome.getBody();
//               assert responseRouteEntity != null;
//               Income routeIncome = (Income) responseRouteEntity.getData();
//               totalRenevue+=routeIncome.getRevenue();
//
//
//
//           }
//           return totalRenevue;
//      }
 @GetMapping("/add/IncomeCoach/{idVehicle}")
public String addCoachIncome (@PathVariable String idVehicle) throws ExecutionException, InterruptedException {
    ResponseEntity<ResponseObject<Income>> responseIncome= calculateIncomeFormCoach(idVehicle);
     ResponseObject<Income> incomeResponseObject = responseIncome.getBody();
     Income incomeObject = (Income) incomeResponseObject.getData();
     String  idIncome = incomeObject.getIdIncome();
     return idIncome;
}
    @GetMapping("/add/IncomeContainer/{idVehicle}")
    public String addContainerIncome (@PathVariable String idVehicle) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<Income>> responseIncome= calculateIncomeFormContainer(idVehicle);
        ResponseObject<Income> incomeResponseObject = responseIncome.getBody();
        Income incomeObject = (Income) incomeResponseObject.getData();
        String  idIncome = incomeObject.getIdIncome();
        return idIncome;
    }

}

