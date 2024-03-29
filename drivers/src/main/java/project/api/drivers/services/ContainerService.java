package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Vehicle;
import project.api.drivers.repositories.ContainerRepository;
import project.api.drivers.models.Container;
import project.api.drivers.repositories.VehicleRepository;

import java.util.concurrent.ExecutionException;

@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public Container getContainerById(String id) throws ExecutionException, InterruptedException {
        Container container = containerRepository.getContainerById(id);
        Vehicle vehicle = vehicleRepository.getVehicleById(id);
        container.setVehicle(vehicle);
        return container;
    }
}
