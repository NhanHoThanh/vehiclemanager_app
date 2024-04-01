package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Route;
import project.api.drivers.repositories.RouteRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public class RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route getRouteById(int id) throws ExecutionException, InterruptedException {
        return routeRepository.getRouteById(id);
    }

    public void deleteRouteById(int id) throws ExecutionException, InterruptedException {
        routeRepository.deleteRouteById(id);
    }

    public void createRoute(Route route) throws ExecutionException, InterruptedException {
        routeRepository.createRoute(route);
    }

    public void updateRoute(Route route) throws ExecutionException, InterruptedException {
        routeRepository.updateRoute(route);
    }

    public List<Route> getAllRoutes() throws ExecutionException, InterruptedException {
        return routeRepository.getAllRoutes();
    }
}
