package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Vehicle;
import project.api.drivers.repositories.VehicleRepository;

import java.util.concurrent.ExecutionException;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    public Vehicle getVehicleById(String id) throws ExecutionException, InterruptedException {
        System.out.println(vehicleRepository.getVehicleById(id));
        return vehicleRepository.getVehicleById(id);
    }
}
