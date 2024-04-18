package project.api.drivers.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//<<<<<<< HEAD
//import project.api.drivers.models.Driver;
//import project.api.drivers.models.Route;
//=======
import org.springframework.web.client.RestTemplate;
//import project.api.drivers.models.Driver;
import project.api.drivers.models.*;

import project.api.drivers.services.VehicleService;
import project.api.drivers.ultis.ResponseObject;

import java.util.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private RestTemplate restTemplate;
    public VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<Vehicle>> getVehicle(@PathVariable String id) throws ExecutionException, InterruptedException {
        ResponseObject<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping()
    public ResponseEntity<ResponseObject<List<Vehicle>>> getAllVehicle() {
        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getAllVehicle();
        if (vehicleList != null) {
            return ResponseEntity.ok(vehicleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject<List<Vehicle>>> getCoachByAttributes(@RequestBody Vehicle vehicle) {
        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getVehicleByAttributes(vehicle);
        if (vehicleList != null) {
            return ResponseEntity.ok(vehicleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //    @GetMapping("/search")
//    public ResponseEntity<ResponseObject<List<Vehicle>>> getVehicleByAttributes(@RequestParam Map<String, String> allParams) {
//        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getVehicleByAttributes(allParams);
//        if (vehicleList != null) {
//            return ResponseEntity.ok(vehicleList);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
    @GetMapping("/searchroute")
    public ResponseEntity<ResponseObject<List<Vehicle>>> getVehicleRoute(@RequestParam Map<String, String> departure, @RequestParam Map<String, String> destination) {
        ResponseObject<List<Vehicle>> vehicleList = vehicleService.getVehicleRoute(departure, destination);
        System.out.println("controller");
        if (vehicleList != null) {
            return ResponseEntity.ok(vehicleList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping
    public ResponseEntity<ResponseObject<Vehicle>> createVehicle(@RequestBody Vehicle vehicle) {
        ResponseObject<Vehicle> responseObject = vehicleService.createVehicle(vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateVehicle(@PathVariable String id,  @RequestBody Vehicle vehicle) {
        ResponseObject responseObject = vehicleService.updateVehicle(id, vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/addDriver/{idVehicle}/{idDriver}")
    public ResponseEntity<ResponseObject> addDriver(@PathVariable String idVehicle, @PathVariable String idDriver,  @RequestBody Vehicle vehicle) {
        ResponseObject responseObject = vehicleService.addDriver(idVehicle, idDriver,  vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @DeleteMapping("/removeDriver/{idVehicle}/{idDriver}")
    public ResponseEntity<ResponseObject> removeDriver(@PathVariable String idVehicle, @PathVariable String idDriver,  @RequestBody Vehicle vehicle) {
        ResponseObject responseObject = vehicleService.removeDriver(idVehicle, idDriver,  vehicle);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Vehicle>> deleteVehicle(@PathVariable String id) {
        ResponseObject<Vehicle> responseObject = vehicleService.deleteVehicle(id);
        if ("error".equals(responseObject.getStatus())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
        return ResponseEntity.ok(responseObject);
    }
    @PutMapping("/totalRevenue/{idVehicle}")
    public ResponseEntity<ResponseObject>  calculateTotalRevenueCostProfit (@PathVariable String idVehicle ) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<Vehicle>> responseEntityVehicle =getVehicle(idVehicle );
        ResponseObject<Vehicle> vehicleResponseObject = responseEntityVehicle.getBody();
        Vehicle vehicle = (Vehicle) vehicleResponseObject.getData();
        Double toTalRevenue=0.0;
        Double toTalProfit=0.0;
        Double toTalCost=0.0;

        List<String> listIdIncome= vehicle.getHistoryIncomeList();
        for(String idIncome : listIdIncome){
            ResponseEntity<ResponseObject<Income>> responseIncome= restTemplate.exchange(
                    "/api/income/{idIncome}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObject<Income>>() {
                    },
                    idIncome
            );
            //
            ResponseObject<Income> responseIncomeEntity = responseIncome.getBody();
            assert responseIncomeEntity != null;
            Income routeData = (Income) responseIncomeEntity.getData();
            toTalRevenue+=routeData.getRevenue();
            toTalProfit=routeData.getProfit();
            toTalCost=routeData.getCost();
        }

        Vehicle vehicleBoby = new Vehicle(toTalRevenue,toTalProfit,toTalCost);
        return  updateVehicle(idVehicle,vehicleBoby);




    }
    @PutMapping("updateRoute/{idVehicle}")
    public ResponseEntity<ResponseObject> updateRouteAndCalculateIncome (@PathVariable String  idVehicle ,@RequestBody Vehicle vehicle) throws ExecutionException, InterruptedException {

        String idIncome="";
        ResponseEntity<ResponseObject<Vehicle>> responseVehicle = getVehicle( idVehicle );

        //
        ResponseObject<Vehicle> responseVehicleEntity = responseVehicle.getBody();
        assert responseVehicleEntity != null;
        Vehicle vehicleData = (Vehicle) responseVehicleEntity.getData();

        if( vehicleData.getVehicleType().equals("coach")){
            ResponseEntity<ResponseObject<String>> responseId = restTemplate.exchange(
                    "/api/income/add/IncomeCoach/{idVehicle}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObject<String>>() {
                    },
                    idVehicle
            );
            //
            ResponseObject<String> responseIdEntity =responseId .getBody();
            assert  responseIdEntity!= null;
            idIncome = (String) responseIdEntity.getData();
        }
        else {
            ResponseEntity<ResponseObject<String>> responseId = restTemplate.exchange(
                    "/api/income/add/IncomeContainer/{idVehicle}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObject<String>>() {
                    },
                    idVehicle
            );
            //
            ResponseObject<String> responseIdEntity =responseId .getBody();
            assert  responseIdEntity!= null;
            idIncome = (String) responseIdEntity.getData();

        }
        List<String> listIdIncomeUpdate = vehicleData.getHistoryIncomeList(); // 1
        listIdIncomeUpdate.add(idIncome);
        List<String> listIdRouteUpdate =vehicleData.getHistoryRouteList();
        listIdRouteUpdate.add(vehicle.getRoute()); // 2
         List<Date> timeStartListUpdate =    vehicle.getTimeStartList() ;
        timeStartListUpdate.add(vehicle.getTimeStart()); // 3
          List<Date>  timeEndListUpdate = vehicle.getTimeEndList();
        timeEndListUpdate.add(vehicle.getTimeEnd()); // 4
        // goi api coach hoac cargo de reset lai
         Vehicle newVehicleUpdate = new Vehicle(listIdRouteUpdate,listIdIncomeUpdate,timeStartListUpdate,timeEndListUpdate);
        return  updateVehicle(idVehicle,newVehicleUpdate);


    }





}