package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.repositories.PassengerRepository;
import project.api.drivers.models.Passenger;
import java.util.concurrent.ExecutionException;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger getPassengerById(String id) throws ExecutionException, InterruptedException {
        return passengerRepository.getPassengerById(id);
    }
}