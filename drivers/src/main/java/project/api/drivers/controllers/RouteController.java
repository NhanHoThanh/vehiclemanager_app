package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Route;
import project.api.drivers.services.RouteService;

import java.util.List;
import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/api/route")
public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable int id) throws ExecutionException, InterruptedException {
        return routeService.getRouteById(id);
    }

    @PostMapping("/create")
    public void createRoute(@RequestBody Route route) throws ExecutionException, InterruptedException {
        routeService.createRoute(route);
    }

    @GetMapping("/all")
    public List<Route> getAllRoutes() throws ExecutionException, InterruptedException {
        return routeService.getAllRoutes();
    }

    @PutMapping("/update")
    public void updateRoute(@RequestBody Route route) throws ExecutionException, InterruptedException {
        routeService.updateRoute(route);
    }

    @DeleteMapping("/{id}")
    public void deleteRouteById(@PathVariable int id) throws ExecutionException, InterruptedException {
        routeService.deleteRouteById(id);
    }
}
