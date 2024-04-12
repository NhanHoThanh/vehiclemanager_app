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
//    public Vehicle getVehicleById(String id) throws ExecutionException, InterruptedException {
//        System.out.println(vehicleRepository.getVehicleById(id));
//        return vehicleRepository.getVehicleById(id);
//    }
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
            Vehicle driverUpdate = vehicleRepository.getVehicleById(id);
            if (driverUpdate != null) {
                // BeanUtils.copyProperties(driverUpdate, vehicle);
                vehicleRepository.updateVehicle(vehicle);
                responseObject.setStatus("success");
                responseObject.setMessage("Update vehicle successfully");
                responseObject.setData(driverUpdate);
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

    public ResponseObject<List<Vehicle>> getVehicleByAttributes(Map<String, String> allParams) {
        ResponseObject<List<Vehicle>> responseObject = new ResponseObject<>();
        try {
            List<Vehicle> vehicle = vehicleRepository.getDocumentsByMultipleAttributes("Vehicle", allParams, Vehicle.class);
            if (vehicle != null && !vehicle.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get vehicle successfully");
                responseObject.setData(vehicle);
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

}