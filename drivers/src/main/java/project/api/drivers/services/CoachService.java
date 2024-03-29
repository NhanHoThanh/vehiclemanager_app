package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Coach;
import project.api.drivers.models.Vehicle;
import project.api.drivers.repositories.CoachRepository;
import project.api.drivers.repositories.VehicleRepository;

import java.util.concurrent.ExecutionException;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Coach getCoachById(String id) throws ExecutionException, InterruptedException {
        Coach coach = coachRepository.getCoachById(id);
        Vehicle vehicle = vehicleRepository.getVehicleById(id);
        coach.setVehicle(vehicle);
        System.out.println(coach);
        return coach;
    }
}
