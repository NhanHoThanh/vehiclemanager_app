package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.*;
import project.api.drivers.repositories.ContainerRepository;
import project.api.drivers.repositories.VehicleRepository;
import project.api.drivers.repositories.CargoRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CargoRepository cargoRepository;

public ResponseObject<List<Container>> getAllContainer() {
    ResponseObject<List<Container>> responseObject = new ResponseObject<>();
    try {
        List<Container> container = containerRepository.getAllDocuments("Container", Container.class);
        List<Vehicle> vehicle = vehicleRepository.getAllDocuments("Vehicle", Vehicle.class);
        if (container.isEmpty() || vehicle.isEmpty()) {
            responseObject.setStatus("success");
            responseObject.setMessage("No container found");
            return responseObject;
        }
        for(int i = 0; i < container.size(); i++) {
            container.get(i).setVehicle(vehicle.get(i));
        }
        responseObject.setStatus("success");
        responseObject.setMessage("Get all container successfully");
        responseObject.setData(container);
    } catch (Exception e) {
        responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
    }
    return responseObject;
}

    public ResponseObject<Container> getContainerById(String id) {
        ResponseObject<Container> responseObject = new ResponseObject<>();
        try {
            Container container = containerRepository.getContainerById(id);
            Vehicle vehicle = vehicleRepository.getVehicleById(id);
            if (container != null && vehicle != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get container by id successfully");
                container.setVehicle(vehicle);
                responseObject.setData(container);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Container not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Container> createContainer(Container container) {
        ResponseObject<Container> responseObject = new ResponseObject<>();
        try {
            Container newContainer = containerRepository.createContainer(container);
            responseObject.setStatus("success");
            responseObject.setMessage("Create container successfully");
            responseObject.setData(newContainer);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    //
//
    public ResponseObject<Container> updateContainer(String id, Container container) {
        ResponseObject<Container> responseObject = new ResponseObject<>();
        try {
            Container containerUpdate = containerRepository.getContainerById(id);
            if (containerUpdate != null) {
                // BeanUtils.copyProperties(containerUpdate, container);
                containerRepository.updateContainer(container);
                responseObject.setStatus("success");
                responseObject.setMessage("Update container successfully");
                responseObject.setData(containerUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Container not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Container> deleteContainer(String id) {
        ResponseObject<Container> responseObject = new ResponseObject<>();
        try {
            Container container = containerRepository.getContainerById(id);
            if (container != null) {
                containerRepository.deleteContainerById(container.getIdVehicle());
                String idDelete = container.getIdVehicle();
                vehicleRepository.deleteVehicleById(idDelete);
                responseObject.setStatus("success");
                responseObject.setMessage("Delete container successfully");
                responseObject.setData(container);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Container not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
        }
        return responseObject;
    }

    public ResponseObject<List<Container>> getContainerByAttributes(Map<String, String> allParams) {
        ResponseObject<List<Container>> responseObject = new ResponseObject<>();
        try {
            List<Container> container = containerRepository.getDocumentsByMultipleAttributes("Container", allParams, Container.class);
            if (container != null && !container.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get container successfully");
                responseObject.setData(container);
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

    public ResponseObject<Container> addCargo(String idVehicle, String idCargo, Boolean BooleanData) {
        ResponseObject<Container> responseObject = new ResponseObject<>();
        try {
            Container containerUpdate = containerRepository.getContainerById(idVehicle);
            if (containerUpdate != null) {
                containerRepository.addCargo(idVehicle,idCargo, BooleanData);
                responseObject.setStatus("success");
                responseObject.setMessage("Update vehicle successfully");
                responseObject.setData(containerUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
        }
        return responseObject;
    }
    public ResponseObject<Container> removeCargo(String idVehicle,String idCargo) {
        ResponseObject<Container> responseObject = new ResponseObject<>();
        try {
            Container containerUpdate = containerRepository.getContainerById(idVehicle);
            if (containerUpdate != null) {
                containerRepository.removeCargo(idVehicle,idCargo);
                responseObject.setStatus("success");
                responseObject.setMessage("Update vehicle successfully");
                responseObject.setData(containerUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Vehicle not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
        }
        return responseObject;
    }
    public ResponseObject<List<Cargo>> getListCargo(String idVehicle) {
        ResponseObject<List<Cargo>> responseObject = new ResponseObject<>();
        try {
            Container container = containerRepository.getContainerById(idVehicle);
            List<Cargo> listCargo = new ArrayList<>();
            if (container == null) {
                responseObject.setStatus("success");
                responseObject.setMessage("No coach found");
                return responseObject;
            }
            for(int i = 0; i < container.getCargoList().size(); i++) {
                String idCargo = container.getCargoList().get(i);
                Cargo cargo = cargoRepository.getCargoById(idCargo);
                listCargo.add(cargo);
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all container successfully");
            responseObject.setData(listCargo);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
}