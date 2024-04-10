package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Passenger;

import java.util.concurrent.ExecutionException;

@Repository
public class PassengerRepository extends GenericRepositoryImpl {
    public Passenger createPassenger(Passenger passenger) throws ExecutionException, InterruptedException {
        createDocument("Passenger", passenger.getIdPassenger(), passenger);
        return passenger;
    }

    public Passenger getPassengerById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Passenger", id, Passenger.class);
    }

    public void updatePassenger(Passenger passenger) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Drivers", passenger.getIdPassenger(), passenger);
    }

    public void deletePassengerById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Passenger", id);
    }
}
