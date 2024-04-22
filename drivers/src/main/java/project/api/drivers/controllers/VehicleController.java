package project.api.drivers.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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

import project.api.drivers.services.CoachService;
import project.api.drivers.services.ContainerService;
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
    @Autowired
    private CoachService coachService;
    @Autowired
    private ContainerService containerService;

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
    public ResponseEntity<ResponseObject<List<Vehicle>>> getVehicleByAttributes(@RequestBody Vehicle vehicle) {
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
            toTalProfit+=routeData.getProfit();
            toTalCost+=routeData.getCost();
        }

        Vehicle vehicleBoby = new Vehicle(toTalRevenue,toTalProfit,toTalCost);
        return  updateVehicle(idVehicle,vehicleBoby);




    }
    @PutMapping("/updateRoute/{idDriver}")
    public ResponseEntity<ResponseObject> updateRouteAndCalculateIncome ( @PathVariable String idDriver  ,@RequestBody Vehicle vehicle ) throws ExecutionException, InterruptedException {

        ResponseEntity<ResponseObject<Driver>> responseDriver = restTemplate.exchange(
                "/api/drivers/{idDriver}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Driver>>() {
                },
                idDriver
        );
        //
        ResponseObject<Driver> responseDriverEntity = responseDriver.getBody();
        assert responseDriverEntity != null;
        Driver driverData = (Driver) responseDriverEntity.getData();
        String idVehicle = driverData.getVehicleId();

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
//        List<Date> timeStartListUpdate = new ArrayList<>();
//         if( vehicle.getTimeStartList()==null) {
//
//
//             vehicle.setTimeStartList(timeStartListUpdate);
//
//         }
////         else {
////             timeStartListUpdate =    vehicle.getTimeStartList() ;
////             timeStartListUpdate.add(vehicle.getTimeStart());
////         }
////         List<Date> timeStartListUpdate =    vehicle.getTimeStartList() ;
////        timeStartListUpdate.add(vehicle.getTimeStart()); // 3
//        List<Date> timeEndListUpdate = new ArrayList<>();
//        if( vehicle.getTimeEndList()==null){
//
//
//            vehicle.setTimeEndList(timeEndListUpdate );
//        }
////        else {
////            timeEndListUpdate =    vehicle.getTimeEndList() ;
////            timeEndListUpdate.add(vehicle.getTimeEnd());
////        }
////          List<Date>  timeEndListUpdate = vehicle.getTimeEndList();
////        timeEndListUpdate.add(vehicle.getTimeEnd()); // 4
//        List<Date> timeStartListUpdateHandler = vehicle.getTimeStartList();
//        timeStartListUpdateHandler.add(vehicle.getTimeStart());
//        List<Date> timeEndListUpdateHandler = vehicle.getTimeEndList();
//        timeEndListUpdateHandler.add(vehicle.getTimeEnd());
//        vehicle.setTimeStartList(timeStartListUpdateHandler);
//        vehicle.setTimeEndList(timeEndListUpdateHandler);
        List<Date> timeStartListUpdate = new ArrayList<>();
        List<Date> timeEndListUpdate = new ArrayList<>();
        if(vehicleData.getTimeStartList()!=null){
            timeStartListUpdate=vehicleData.getTimeStartList();
        }
        if(vehicleData.getTimeEndList()!=null){
            timeEndListUpdate=vehicleData.getTimeEndList();
        }
        timeStartListUpdate.add(vehicle.getTimeStart());
        timeEndListUpdate.add(vehicle.getTimeEnd());






        // goi api coach hoac cargo de reset lai
        if(vehicleData.getVehicleType().equals("coach")) {
            ResponseObject<Coach> coachResponseObject = coachService.getCoachById(idVehicle);
            Coach coachData = (Coach) coachResponseObject.getData();
            int numberOfSeat =coachData.getNumberOfSeats();
            List<String> emptySeat = new ArrayList<>();
            for(int i=1;i<=numberOfSeat ;i++){
                emptySeat.add( String.valueOf(i));
            }
            List<String> passengerList = new ArrayList<>();





            Coach coach = new Coach(0,emptySeat,passengerList);
            coachService.updateCoach(idVehicle,coach );
        }
        else {
            ResponseObject<Container> containerResponseObject = containerService.getContainerById(idVehicle);
            Container containerData = (Container) containerResponseObject.getData();

            List<String> cargoList = new ArrayList<>();





            Container container = new Container(0,cargoList);
            containerService.updateContainer(idVehicle,container );
        }


         Driver newDriverUpdate = new Driver();
        newDriverUpdate.setRouteId(vehicle.getRoute());
        ResponseEntity<ResponseObject<Driver>> responseId = restTemplate.exchange(
                "/api/drivers/{idDriver}",
                HttpMethod.PUT, // Use PUT method for updating data
                new HttpEntity<>(newDriverUpdate), // Pass newDriverUpdate as request body
                new ParameterizedTypeReference<ResponseObject<Driver>>() {},
                idDriver// Pass id as a path variable
        );

        //
//        ResponseObject<String> responseIdEntity =responseId .getBody();
//        assert  responseIdEntity!= null;
//        idIncome = (String) responseIdEntity.getData();
        String idRoute = vehicle.getRoute();
        ResponseEntity<ResponseObject<Route>> responseRoute = restTemplate.exchange(
                "/api/route/{idRoute}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseObject<Route>>() {
                },
                idRoute
        );
        //
        ResponseObject<Route> responseRouteEntity = responseRoute.getBody();
        assert responseRouteEntity != null;
        Route routeData = (Route) responseRouteEntity.getData();
        String newUpdateDeparture = routeData.getDeparture();
        String newUpdateDestination =  routeData.getDestination();
        System.out.println(newUpdateDeparture );
        System.out.println(newUpdateDestination);


//        Vehicle newVehicleUpdate = new Vehicle(listIdRouteUpdate,listIdIncomeUpdate,timeStartListUpdate,timeEndListUpdate,
//                vehicle.getRoute(),vehicle.getTimeStart(),vehicle.getTimeEnd(),newUpdateDeparture,newUpdateDestination);
//
//        ResponseEntity<ResponseObject> updateVehicleReturn = updateVehicle(idVehicle,newVehicleUpdate);
        Coach newCoachUpdate = new Coach(listIdRouteUpdate,listIdIncomeUpdate,timeStartListUpdate,timeEndListUpdate,
               vehicle.getRoute(),vehicle.getTimeStart(),vehicle.getTimeEnd(),newUpdateDeparture,newUpdateDestination);
        ResponseEntity<ResponseObject> responseCoach  = restTemplate.exchange(
                "/api/coach/{idVehicle}",
                HttpMethod.PUT, // Use PUT method for updating data
                new HttpEntity<>(newCoachUpdate ), // Pass newDriverUpdate as request body
                new ParameterizedTypeReference<ResponseObject>() {},
                idVehicle// Pass id as a path variable
        );
        calculateTotalRevenueCostProfit(idVehicle);
        return responseCoach;


    }
    @GetMapping("/history/route/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Route>>>  getListRouteHistory (@PathVariable String idVehicle ) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<Vehicle>> responseVehicle = getVehicle(idVehicle);

        ResponseObject<Vehicle> responseVehicleEntity = responseVehicle.getBody();
        assert responseVehicleEntity != null;
        Vehicle vehicleData = (Vehicle) responseVehicleEntity.getData();
        List<String> historyRouteList = vehicleData.getHistoryRouteList();
        List<Route> listRoute = new ArrayList<>();
        for (String idRoute : historyRouteList  ) {
            ResponseEntity<ResponseObject<Route>> responseRoute = restTemplate.exchange(
                    "/api/route/{idRoute}",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseObject<Route>>() {
                    },
                    idRoute
            );
            //
            ResponseObject<Route> responseRouteEntity = responseRoute.getBody();
            assert responseRouteEntity != null;
            Route routeData = (Route) responseRouteEntity.getData();
            listRoute.add(routeData);
        }
        ResponseObject<List<Route>> responseObject = new ResponseObject<>();
        responseObject.setStatus("success");
        responseObject.setMessage("Get all Route successfully");
        responseObject.setData(listRoute );
        return ResponseEntity.ok(responseObject);

    }
    @GetMapping("/history/income/{idVehicle}")
    public ResponseEntity<ResponseObject<List<Income>>>  getListIncomeHistory (@PathVariable String idVehicle ) throws ExecutionException, InterruptedException {
        ResponseEntity<ResponseObject<Vehicle>> responseVehicle = getVehicle(idVehicle);

        ResponseObject<Vehicle> responseVehicleEntity = responseVehicle.getBody();
        assert responseVehicleEntity != null;
        Vehicle vehicleData = (Vehicle) responseVehicleEntity.getData();
        List<String> historyIncomeList = vehicleData.getHistoryIncomeList();
        List<Income> listIncome = new ArrayList<>();
        for (String idIncome : historyIncomeList  ) {
            ResponseEntity<ResponseObject<Income>> responseIncome = restTemplate.exchange(
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
            Income IncomeData = (Income) responseIncomeEntity.getData();
            listIncome.add(IncomeData);
        }
        ResponseObject<List<Income>> responseObject = new ResponseObject<>();
        responseObject.setStatus("success");
        responseObject.setMessage("Get all Income successfully");
        responseObject.setData(listIncome );
        return ResponseEntity.ok(responseObject);

    }







}