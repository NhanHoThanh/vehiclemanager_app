package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Coach;
import project.api.drivers.models.Vehicle;
import project.api.drivers.repositories.CoachRepository;
import project.api.drivers.repositories.VehicleRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    //    public Coach getCoachById(String id) throws ExecutionException, InterruptedException {
//        Coach coach = coachRepository.getCoachById(id);
//        Vehicle vehicle = vehicleRepository.getVehicleById(id);
//        coach.setVehicle(vehicle);
//        System.out.println(coach);
//        return coach;
//    }
    public ResponseObject<List<Coach>> getAllCoach() {
        ResponseObject<List<Coach>> responseObject = new ResponseObject<>();
        try {
            List<Coach> coach = coachRepository.getAllDocuments("Coach", Coach.class);
            List<Vehicle> vehicle = vehicleRepository.getAllDocuments("Vehicle", Vehicle.class);
            if (coach.isEmpty() || vehicle.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No coach found");
                return responseObject;
            }
            for(int i = 0; i < coach.size(); i++) {
                coach.get(i).setVehicle(vehicle.get(i));
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all coach successfully");
            responseObject.setData(coach);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Coach> getCoachById(String id) {
        ResponseObject<Coach> responseObject = new ResponseObject<>();
        try {
            Coach coach = coachRepository.getCoachById(id);
            Vehicle vehicle = vehicleRepository.getVehicleById(id);
            if (coach != null && vehicle != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get coach by id successfully");
                coach.setVehicle(vehicle);
                responseObject.setData(coach);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Coach not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<Coach> createCoach(Coach coach) {
        ResponseObject<Coach> responseObject = new ResponseObject<>();
        try {
            Coach newCoach = coachRepository.createCoach(coach);
            responseObject.setStatus("success");
            responseObject.setMessage("Create coach successfully");
            responseObject.setData(newCoach);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    //
//
    public ResponseObject<Coach> updateCoach(String id, Coach coach) {
        ResponseObject<Coach> responseObject = new ResponseObject<>();
        try {
            Coach coachUpdate = coachRepository.getCoachById(id);
            if (coachUpdate != null) {
                // BeanUtils.copyProperties(coachUpdate, coach);
                coachRepository.updateCoach(coach);
                responseObject.setStatus("success");
                responseObject.setMessage("Update coach successfully");
                responseObject.setData(coachUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Coach not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Coach> deleteCoach(String id) {
        ResponseObject<Coach> responseObject = new ResponseObject<>();
        try {
            Coach coach = coachRepository.getCoachById(id);
            if (coach != null) {
                String idDelete = coach.getIdVehicle();
                vehicleRepository.deleteVehicleById(idDelete);
                coachRepository.deleteCoachById(idDelete);
                responseObject.setStatus("success");
                responseObject.setMessage("Delete coach successfully");
                responseObject.setData(coach);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Coach not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<List<Coach>> getCoachByAttributes(Map<String, String> allParams) {
        ResponseObject<List<Coach>> responseObject = new ResponseObject<>();
        try {
            List<Coach> coach = coachRepository.getDocumentsByMultipleAttributes("Coach", allParams, Coach.class);
            if (coach != null && !coach.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get coach successfully");
                responseObject.setData(coach);
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