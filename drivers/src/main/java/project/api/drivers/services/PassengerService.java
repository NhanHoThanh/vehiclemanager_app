package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Passenger;
import project.api.drivers.repositories.PassengerRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Map;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public ResponseObject<Passenger> getPassengerById(String id) {
        ResponseObject<Passenger> responseObject = new ResponseObject<>();
        try {
            Passenger passenger = passengerRepository.getPassengerById(id);
            if (passenger != null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get Passenger by id successfully");
                responseObject.setData(passenger);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Passenger not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<List<Passenger>> getAllPassenger() {
        ResponseObject<List<Passenger>> responseObject = new ResponseObject<>();
        try {
            List<Passenger> passenger = passengerRepository.getAllDocuments("Passenger", Passenger.class);
            if (passenger.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No Passenger found");
                return responseObject;
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all Passenger successfully");
            responseObject.setData(passenger);
        } catch (Exception e) {
            responseObject.setStatus("error");
    //            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }


    public ResponseObject<Passenger> createPassenger(Passenger passenger) {
        ResponseObject<Passenger> responseObject = new ResponseObject<>();
        try {
            Passenger newPassenger = passengerRepository.createPassenger(passenger);
            responseObject.setStatus("success");
            responseObject.setMessage("Create Passenger successfully");
            responseObject.setData(newPassenger);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    //
//
    public ResponseObject<Passenger> updatePassenger(String id, Passenger passenger) {
        ResponseObject<Passenger> responseObject = new ResponseObject<>();
//        System.out.println(passenger.getName());
        try {
            Passenger passengerUpdate = passengerRepository.getPassengerById(id);
//            System.out.println(p);
            if (passengerUpdate != null) {
                passengerRepository.updatePassenger(id, passenger);
                responseObject.setStatus("success");
                responseObject.setMessage("Update Passenger successfully");
                responseObject.setData(passengerUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Passenger not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Passenger> deletePassenger(String id) {
        ResponseObject<Passenger> responseObject = new ResponseObject<>();
        try {
            Passenger passenger = passengerRepository.getPassengerById(id);
            if (passenger != null) {
                passengerRepository.deletePassengerById(passenger.getIdPassenger());
                responseObject.setStatus("success");
                responseObject.setMessage("Delete Passenger successfully");
                responseObject.setData(passenger);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Passenger not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }

    public ResponseObject<List<Passenger>> getPassengerByAttributes(Map<String, String> allParams) {
        ResponseObject<List<Passenger>> responseObject = new ResponseObject<>();
        try {
            List<Passenger> passenger = passengerRepository.getDocumentsByMultipleAttributes("passenger", allParams, Passenger.class);
            if (passenger != null && !passenger.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get passenger successfully");
                responseObject.setData(passenger);
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