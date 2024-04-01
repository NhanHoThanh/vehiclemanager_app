package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Cargo;
import project.api.drivers.repositories.CargoRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    public ResponseObject<List<Cargo>> getAllCargo() {
        ResponseObject<List<Cargo>> responseObject = new ResponseObject<>();
        try {
            List<Cargo> Cargo = cargoRepository.getAllDocuments("Cargo", Cargo.class);
            if (Cargo.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No cargo found");
                return responseObject;
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all Cargo successfully");
            responseObject.setData(Cargo);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Cargo> getCargoById(String id) {
        ResponseObject<Cargo> responseObject = new ResponseObject<>();
        try {
            Cargo cargo = cargoRepository.getCargoById(id);
            if (cargo != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get cargo by id successfully");
                responseObject.setData(cargo);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Cargo not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Cargo> createCargo(Cargo cargo) {
        ResponseObject<Cargo> responseObject = new ResponseObject<>();
        try {
            Cargo newCargo = cargoRepository.createCargo(cargo);
            responseObject.setStatus("success");
            responseObject.setMessage("Create cargo successfully");
            responseObject.setData(newCargo);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    //
//
    public ResponseObject<Cargo> updateCargo(String id, Cargo cargo) {
        ResponseObject<Cargo> responseObject = new ResponseObject<>();
        try {
            Cargo cargoUpdate = cargoRepository.getCargoById(id);
            if (cargoUpdate != null) {
                // BeanUtils.copyProperties(cargoUpdate, cargo);
                cargoRepository.updateCargo(cargo);
                responseObject.setStatus("success");
                responseObject.setMessage("Update cargo successfully");
                responseObject.setData(cargoUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Cargo not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Cargo> deleteCargo(String id) {
        ResponseObject<Cargo> responseObject = new ResponseObject<>();
        try {
            Cargo cargo = cargoRepository.getCargoById(id);
            if (cargo != null) {
                cargoRepository.deleteCargoById(cargo.getIdCargo());
                responseObject.setStatus("success");
                responseObject.setMessage("Delete cargo successfully");
                responseObject.setData(cargo);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Cargo not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<List<Cargo>> getCargoByAttributes(Map<String, String> allParams) {
        ResponseObject<List<Cargo>> responseObject = new ResponseObject<>();
        try {
            List<Cargo> Cargo = cargoRepository.getDocumentsByMultipleAttributes("Cargo", allParams, Cargo.class);
            if (Cargo != null && !Cargo.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get Cargo successfully");
                responseObject.setData(Cargo);
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