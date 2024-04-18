package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.api.drivers.models.*;
import project.api.drivers.services.RouteService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/api/route")
public class RouteController {
//    private final RouteService routeService;
//
//    @Autowired
//    public RouteController(RouteService routeService) {
//        this.routeService = routeService;
//    }
//
//    @GetMapping("/{id}")
//    public Route getRouteById(@PathVariable int id) throws ExecutionException, InterruptedException {
//        return routeService.getRouteById(id);
//    }
//
//    @PostMapping("/create")
//    public void createRoute(@RequestBody Route route) throws ExecutionException, InterruptedException {
//        routeService.createRoute(route);
//    }
//
//    @GetMapping("/all")
//    public List<Route> getAllRoutes() throws ExecutionException, InterruptedException {
//        return routeService.getAllRoutes();
//    }
//
//    @PutMapping("/update")
//    public void updateRoute(@RequestBody Route route) throws ExecutionException, InterruptedException {
//        routeService.updateRoute(route);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteRouteById(@PathVariable int id) throws ExecutionException, InterruptedException {
//        routeService.deleteRouteById(id);
//    }
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RouteService routeService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Route>> getRoute(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject< Route> route = routeService.getRouteById(id);
        if (route != null) {
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Route>>> getAllRoute() {
        ResponseObject<List<Route>> routeList = routeService.getAllRoute();
        if (routeList != null) {
            return ResponseEntity.ok(routeList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping
    public ResponseEntity<ResponseObject<Route>> createRoute(@RequestBody Route route) {
        ResponseObject<Route> responseObject = routeService.createRoute(route);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateRoute(@PathVariable String id,  @RequestBody Route route) {
        ResponseObject responseObject = routeService.updateRoute(id,route);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Route>> deleteRoute(@PathVariable String id) {
        ResponseObject<Route> responseObject = routeService.deleteRoute(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @GetMapping("checkRoute/{idVehicle}")
    public ResponseEntity<ResponseObject<Boolean>> checkRoute (@PathVariable String idVehicle, @RequestParam Map<String, String> data ){
        ResponseEntity<ResponseObject<Vehicle>> responseEntityVehicle = restTemplate.exchange(
                "/api/coach/{idVehicle}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Vehicle>>() {
                },
                idVehicle
        );
        ResponseObject<Vehicle>  responseVehicle =  responseEntityVehicle.getBody();
        Vehicle vehicle =  (Vehicle )responseVehicle.getData();
        String route = vehicle.getRoute();
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
        List<String> listIdRoute= routeData.getStation();


        for( String IDRoute : listIdRoute ){

            ResponseEntity<ResponseObject<Route>> responseRouteInList = restTemplate.exchange(
                    "/api/route/{IDRoute}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObject<Route>>() {
                    },
                    IDRoute
            );
            ResponseObject<Route> responseRouteEntityInList = responseRouteInList.getBody();
            assert responseRouteEntityInList  != null;
            Route routeDataInList = (Route) responseRouteEntityInList.getData();

            if(routeDataInList.getDeparture().equals(data.get("departure")) && routeDataInList.getDestination().equals(data.get("destination")) ){
                ResponseObject<Boolean> responseObject = new ResponseObject<>();


                        responseObject.setStatus("success");
                        responseObject.setMessage("Check successfully");
                        responseObject.setData(true);
                return ResponseEntity.ok(responseObject);
                }


            }
        ResponseObject<Boolean> responseObjectCaseFalse = new ResponseObject<>();

        responseObjectCaseFalse.setStatus("success");
                responseObjectCaseFalse.setMessage("Check successfully");
        responseObjectCaseFalse.setData(false);
        return ResponseEntity.ok(responseObjectCaseFalse );

        }
//    @GetMapping("/add/RouteCoach/{idVehicle}/{idRoute}")
//    public String addCoachIncome (@PathVariable String idVehicle, @PathVariable String idRoute) throws ExecutionException, InterruptedException {
//        ResponseEntity<ResponseObject<Route>> responseRoute= getRoute(idRoute);
//        ResponseObject<Route> routeResponseObject = responseRoute.getBody();
//        Route routeObject = (Route) routeResponseObject.getData();
//        String  idRoute = routeObject.getIdRoute();
//        return idRoute;
//    }
//    @GetMapping("/add/RouteContainer/{idVehicle}")
//    public String addContainerIncome (@PathVariable String idVehicle) throws ExecutionException, InterruptedException {
//        ResponseEntity<ResponseObject<Income>> responseIncome= calculateIncomeFormContainer(idVehicle);
//        ResponseObject<Income> incomeResponseObject = responseIncome.getBody();
//        Income incomeObject = (Income) incomeResponseObject.getData();
//        String  idIncome = incomeObject.getIdIncome();
//        return idIncome;
//    }
//    update



}
