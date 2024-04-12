package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Route;

import java.util.concurrent.ExecutionException;
@Repository

public class RouteRepository extends GenericRepositoryImpl  {
    public Route createRoute(Route route) throws ExecutionException, InterruptedException {
        createDocument("Route", route.getIdRoute(), route);
        return route;
    }

    public Route getRouteById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Route", id, Route.class);
    }

    public void updateRoute(Route route) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Route", route.getIdRoute(), route);
    }

    public void deleteRouteById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Route", id);
    }
}
