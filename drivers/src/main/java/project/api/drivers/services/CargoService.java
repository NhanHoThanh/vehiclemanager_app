package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Cargo;
import project.api.drivers.repositories.CargoRepository;
import java.util.concurrent.ExecutionException;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    public Cargo getCargoById(String id) throws ExecutionException, InterruptedException {
        return cargoRepository.getCargoById(id);
    }
}