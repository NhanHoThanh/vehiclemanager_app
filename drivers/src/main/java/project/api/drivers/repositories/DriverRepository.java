package project.api.drivers.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Driver;

import java.util.concurrent.ExecutionException;

public interface DriverRepository {
    Driver getDriverById(String id) throws ExecutionException, InterruptedException;

}