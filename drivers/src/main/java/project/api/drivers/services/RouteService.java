package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Route;
import project.api.drivers.repositories.RouteRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
@Service

public class RouteService {


    @Autowired
    private RouteRepository routeRepository ;
    public ResponseObject<Route> updateRoute(String id, Route route ) {
        ResponseObject<Route> responseObject = new ResponseObject<>();
        try {
            Route routeUpdate = routeRepository.getRouteById(id);
            if (routeUpdate!= null) {
                routeRepository.updateRoute(route);
                responseObject.setStatus("success");
                responseObject.setMessage("Update Route successfully");
                responseObject.setData(routeUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Route not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;

    }
    public ResponseObject<Route> createRoute(Route route) {
        ResponseObject<Route> responseObject = new ResponseObject<>();
        try {
            Route newRoute = routeRepository.createRoute(route);
            responseObject.setStatus("success");
            responseObject.setMessage("Create Route successfully");
            responseObject.setData(newRoute);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Route> getRouteById(String id) {
        ResponseObject<Route> responseObject = new ResponseObject<>();
        try {
            Route route = routeRepository.getRouteById(id);
            if (route != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get Route by id successfully");
                responseObject.setData(route);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Route not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Route> deleteRoute(String id) {
        ResponseObject<Route> responseObject = new ResponseObject<>();
        try {
            Route route = routeRepository.getRouteById(id);
            if (route != null) {
                routeRepository.deleteRouteById(route.getIdRoute());
                responseObject.setStatus("success");
                responseObject.setMessage("Delete Route successfully");
                responseObject.setData(route);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Route not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<List<Route>> getAllRoute() {
        ResponseObject<List<Route>> responseObject = new ResponseObject<>();
        try {
            List<Route> route = routeRepository.getAllDocuments("Route", Route.class);
            if (route.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No Route found");
                return responseObject;
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all Route successfully");
            responseObject.setData(route);
        } catch (Exception e) {
            responseObject.setStatus("error");
            //            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
}
