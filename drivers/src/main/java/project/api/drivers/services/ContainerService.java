package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Container;
import project.api.drivers.models.Vehicle;
import project.api.drivers.repositories.ContainerRepository;
import project.api.drivers.repositories.VehicleRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;

@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

//    public Container getContainerById(String id) throws ExecutionException, InterruptedException {
//        Container container = containerRepository.getContainerById(id);
//        Vehicle vehicle = vehicleRepository.getVehicleById(id);
//        container.setVehicle(vehicle);
//        return container;
//    }
public ResponseObject<List<Container>> getAllContainer() {
    ResponseObject<List<Container>> responseObject = new ResponseObject<>();
    try {
        List<Container> container = containerRepository.getAllDocuments("Container", Container.class);
        if (container.isEmpty()) {
            responseObject.setStatus("success");
            responseObject.setMessage("No container found");
            return responseObject;
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
            container.setVehicle(vehicle);
            if (container != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get container by id successfully");
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
            Container driverUpdate = containerRepository.getContainerById(id);
            if (driverUpdate != null) {
                // BeanUtils.copyProperties(driverUpdate, container);
                containerRepository.updateContainer(container);
                responseObject.setStatus("success");
                responseObject.setMessage("Update container successfully");
                responseObject.setData(driverUpdate);
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
                responseObject.setStatus("success");
                responseObject.setMessage("Delete container successfully");
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
}