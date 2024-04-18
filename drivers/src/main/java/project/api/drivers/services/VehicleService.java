package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Vehicle;
import project.api.drivers.repositories.VehicleRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
//    @Autowired
//    private DriverRepository driverRepository;

    public ResponseObject<List<Vehicle>> getAllVehicle() {
        ResponseObject<List<Vehicle>> responseObject = new ResponseObject<>();
        try {
            List<Vehicle> vehicle = vehicleRepository.getAllDocuments("Vehicle", Vehicle.class);
            if (vehicle.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No vehicle found");
                return responseObject;
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all vehicle successfully");
            responseObject.setData(vehicle);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Vehicle> getVehicleById(String id) {
        ResponseObject<Vehicle> responseObject = new ResponseObject<>();
        try {
            Vehicle vehicle = vehicleRepository.getVehicleById(id);
            if (vehicle != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get vehicle by id successfully");
                responseObject.setData(vehicle);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Vehicle> createVehicle(Vehicle vehicle) {
        ResponseObject<Vehicle> responseObject = new ResponseObject<>();
        try {
            Vehicle newVehicle = vehicleRepository.createVehicle(vehicle);
            responseObject.setStatus("success");
            responseObject.setMessage("Create vehicle successfully");
            responseObject.setData(newVehicle);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    //
//
    public ResponseObject<Vehicle> updateVehicle(String id, Vehicle vehicle) {
        ResponseObject<Vehicle> responseObject = new ResponseObject<>();
        try {
            Vehicle vehicleUpdate = vehicleRepository.getVehicleById(id);
            if (vehicleUpdate != null) {
                vehicleRepository.updateVehicle(id, vehicle);

                responseObject.setStatus("success");
                responseObject.setMessage("Update vehicle successfully");
                responseObject.setData(vehicleUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
        }
        return responseObject;
    }
    public ResponseObject<Vehicle> addDriver(String idVehicle,String idDriver, Vehicle vehicle) {
        ResponseObject<Vehicle> responseObject = new ResponseObject<>();
        try {
            Vehicle vehicleUpdate = vehicleRepository.getVehicleById(idVehicle);
            if (vehicleUpdate != null) {
                vehicleRepository.addDriver(idVehicle,idDriver, vehicle);
                responseObject.setStatus("success");
                responseObject.setMessage("removeDriver successfully");
                responseObject.setData(vehicleUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
        }
        return responseObject;
    }
    public ResponseObject<Vehicle> removeDriver(String idVehicle,String idDriver, Vehicle vehicle) {
        ResponseObject<Vehicle> responseObject = new ResponseObject<>();
        try {
            Vehicle vehicleUpdate = vehicleRepository.getVehicleById(idVehicle);
            if (vehicleUpdate != null) {
                vehicleRepository.removeDriver(idVehicle,idDriver, vehicle);
                responseObject.setStatus("success");
                responseObject.setMessage("removeDriver successfully");
                responseObject.setData(vehicleUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Vehicle> deleteVehicle(String id) {
        ResponseObject<Vehicle> responseObject = new ResponseObject<>();
        try {
            Vehicle vehicle = vehicleRepository.getVehicleById(id);
            if (vehicle != null) {
                vehicleRepository.deleteVehicleById(vehicle.getIdVehicle());
                responseObject.setStatus("success");
                responseObject.setMessage("Delete vehicle successfully");
                responseObject.setData(vehicle);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    //    public ResponseObject<List<Vehicle>> getVehicleByAttributes(Map<String, String> allParams) {
//        ResponseObject<List<Vehicle>> responseObject = new ResponseObject<>();
//        try {
//            List<Vehicle> vehicle = vehicleRepository.getDocumentsByMultipleAttributes("Vehicle", allParams, Vehicle.class);
//            if (vehicle != null && !vehicle.isEmpty()) {
//                responseObject.setStatus("success");
//                responseObject.setMessage("Get vehicle successfully");
//                responseObject.setData(vehicle);
//            } else {
//                responseObject.setStatus("fail");
//                responseObject.setMessage("Loi o day");
//            }
//        } catch (Exception e) {
//            responseObject.setStatus("error");
////            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
//        }
//        return responseObject;
//    }
    public ResponseObject<List<Vehicle>> getVehicleByAttributes(Vehicle vehicle) {
        ResponseObject<List<Vehicle>> responseObject = new ResponseObject<>();
        try {
            List<Vehicle> listVehicle = vehicleRepository.getVehicleByAttributes(vehicle);
            if (listVehicle != null && !listVehicle.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get vehicle successfully");
                responseObject.setData(listVehicle);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Loi o day");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }


    public ResponseObject<List<Vehicle>> getVehicleRoute(Map<String, String> departure, Map<String, String> destination) {
        ResponseObject<List<Vehicle>> responseObject = new ResponseObject<>();
        try {
//            List<Vehicle> vehicles1 = vehicleRepository.getDocumentsByMultipleAttributes("Vehicle", departure, Vehicle.class);
//            List<Vehicle> vehicles2 = vehicleRepository.getDocumentsByMultipleAttributes("Vehicle", destination, Vehicle.class);
            List<Vehicle> vehicles = vehicleRepository.getDocumentsByMultipleAttributes(departure, destination);
            System.out.println("Service");
            if (!vehicles.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get vehicles successfully");
                responseObject.setData(vehicles);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("No vehicles found for the given departure and destination.");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
            responseObject.setMessage("An error occurred: " + e.getMessage());
        }
        return responseObject;
    }

}