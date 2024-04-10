package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Driver;

import java.util.concurrent.ExecutionException;

@Repository
public class DriverRepository extends GenericRepositoryImpl {

    public Driver createDriver(Driver driver) throws ExecutionException, InterruptedException {
        createDocument("Drivers", driver.getId(), driver);
        return driver;
    }

    public Driver getDriverById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Drivers", id, Driver.class);
    }

    public void updateDriver(String Id, Driver driver) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Drivers", Id, driver);
    }

    public void deleteDriverById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Drivers", id);
    }
}