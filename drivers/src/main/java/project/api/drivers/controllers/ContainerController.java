package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.api.drivers.models.*;
import project.api.drivers.models.Container;
import project.api.drivers.services.CargoService;
import project.api.drivers.services.ContainerService;
import project.api.drivers.ultis.ResponseObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/api/container")
public class ContainerController {
    @Autowired
    private RestTemplate restTemplate;
    public ContainerService containerService;
    public CargoService cargoService;

    public ContainerController(ContainerService containerService, CargoService cargoService) {
        this.containerService = containerService;
        this.cargoService = cargoService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Container>> getContainer(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Container> container = containerService.getContainerById(id);
        if (container != null) {
            return ResponseEntity.ok(container);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<java.util.List<Container>>> getAllContainer() {
        ResponseObject<java.util.List<Container>> containerList = containerService.getAllContainer();
        if (containerList != null) {
            return ResponseEntity.ok(containerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/cargo")
    public ResponseEntity<ResponseObject<List<Cargo>>> getAllCargo() {
        ResponseObject<List<Cargo>> cargoList = cargoService.getAllCargo();
        if (cargoList != null) {
            return ResponseEntity.ok(cargoList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/listCargo/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Cargo>>> getListCargo(@PathVariable String idVehicle) {
        ResponseObject<List<Cargo>> listCargo = containerService.getListCargo(idVehicle);
        if (listCargo != null) {
            return ResponseEntity.ok(listCargo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject<java.util.List<Container>>> getContainerByAttributes(@RequestParam Map<String, String> allParams) {
        ResponseObject<List<Container>> containerList = containerService.getContainerByAttributes(allParams);
        if (containerList != null) {
            return ResponseEntity.ok(containerList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject<Container>> createContainer(@RequestBody Container container) {
        ResponseObject<Container> responseObject = containerService.createContainer(container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateContainer(@PathVariable String id,  @RequestBody Container container) {
        ResponseObject responseObject = containerService.updateContainer(id, container);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Container>> deleteContainer(@PathVariable String id) {
        ResponseObject<Container> responseObject = containerService.deleteContainer(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/addCargo/{idVehicle}/{idCargo}")
    public ResponseEntity<ResponseObject> addCargo(@PathVariable String idVehicle, @PathVariable String idCargo) {
        ResponseEntity<ResponseObject<Cargo>> responseEntityCargo = restTemplate.exchange(
                "/api/cargo/{idCargo}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Cargo>>() {
                },
                idCargo
        );
        ResponseObject<Cargo>  responseCargo =  responseEntityCargo.getBody();
        Cargo cargo =  (Cargo)responseCargo.getData();
        String routeDeparture = cargo.getSendingPlace();
        String routeDestination = cargo.getReceivingPlace();
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
        ResponseObject responseObject = containerService.addCargo(idVehicle, idCargo, BooleanData);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @DeleteMapping("/removeCargo/{idVehicle}/{idCargo}")
    public ResponseEntity<ResponseObject> removeCargo(@PathVariable String idVehicle, @PathVariable String idCargo) {
        ResponseObject responseObject = containerService.removeCargo(idVehicle, idCargo);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @GetMapping("/setContainer/{license}")
    public ResponseEntity<ResponseObject<List<Container>>> getContainerByLicense (@PathVariable String license ){
        //        B1,B2,C,D,E,F
//        ResponseEntity<ResponseObject<Driver>> responseDriver = restTemplate.exchange(
//                "/api/drivers/{idDriver}",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<ResponseObject<Driver>>() {
//                },
//                idDriver
//        );
//        //
//        ResponseObject<Driver> responseDriverEntity = responseDriver.getBody();
//        assert responseDriverEntity != null;
//        Driver driverData = (Driver) responseDriverEntity.getData();
//        String license= driverData.getLicense();


        Double minLoad=-1.0;
        Double maxLoad =-1.0;
        if(license.equals("B1")){
            minLoad=0.0;
            maxLoad =3.5;

        }
        else if( license.equals("B2")){
            minLoad=0.0;
            maxLoad =3.5;
        }
        else if( license.equals("C")){
            minLoad=3.5;
            maxLoad =200.0;
        }
        else if( license.equals("D")){
            minLoad=0.0;
            maxLoad =200.0;
        }
        else if( license.equals("E")){
            minLoad=0.0;
            maxLoad =200.0;
        }
        else if( license.equals("F")){
            minLoad=0.0;
            maxLoad =200.0;
        }

        ResponseEntity<ResponseObject<List<Container>>> responseEntityContainer =  getAllContainer();
        ResponseObject<List<Container>> ContainerResponseObject = responseEntityContainer.getBody();
        List<Container> listContainer = (List<Container>) ContainerResponseObject.getData();
        List<Container> listContainerFilter = new ArrayList<>();
        for(Container Container : listContainer){
            Double load = Container.getMaxLoad();
            if(load<=maxLoad&&load>=minLoad){
                listContainerFilter.add(Container);
            }
        }
        ResponseObject<List<Container>> newResponseListContainer = new ResponseObject<>();
        newResponseListContainer.setData(listContainerFilter);
        return ResponseEntity.ok(newResponseListContainer);


    }
}