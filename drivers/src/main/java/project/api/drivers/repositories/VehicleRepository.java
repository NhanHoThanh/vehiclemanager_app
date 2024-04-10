package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Vehicle;

import java.util.concurrent.ExecutionException;
@Repository
public class VehicleRepository extends GenericRepositoryImpl {

    public Vehicle createVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException {
        createDocument("Vehicle", vehicle.getIdVehicle(), vehicle);
        return vehicle;
    }

    public Vehicle getVehicleById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Vehicle", id, Vehicle.class);
    }

    public void updateVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Vehicle", vehicle.getIdVehicle(), vehicle);
    }

    public void deleteVehicleById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Vehicle", id);
    }
}