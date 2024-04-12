package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Passenger;
import project.api.drivers.models.Route;
import project.api.drivers.services.RouteService;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
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

}
