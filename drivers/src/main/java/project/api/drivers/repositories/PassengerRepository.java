package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Passenger;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public class PassengerRepository extends GenericRepositoryImpl {
    public Passenger createPassenger(Passenger passenger) throws ExecutionException, InterruptedException {
        passenger.setIdPassenger(UUID.randomUUID().toString());
        createDocument("Passenger", passenger.getIdPassenger(), passenger);
        return passenger;
    }

    public Passenger getPassengerById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Passenger", id, Passenger.class);
    }

    public void updatePassenger(String id, Passenger passenger) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Passenger", id, passenger);
    }

    public void deletePassengerById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Passenger", id);
    }
}
