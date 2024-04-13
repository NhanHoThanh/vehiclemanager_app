package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Cargo;

import java.util.concurrent.ExecutionException;

@Repository
public class CargoRepository extends GenericRepositoryImpl {

    public Cargo createCargo(Cargo cargo) throws ExecutionException, InterruptedException {
        createDocument("Cargo", cargo.getIdCargo(), cargo);
        return cargo;
    }

    public Cargo getCargoById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Cargo", id, Cargo.class);
    }

    public void updateCargo(Cargo cargo) throws ExecutionException, InterruptedException, IllegalAccessException{
        updateDocument("Cargo", cargo.getIdCargo(), cargo);
    }

    public void deleteCargoById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Cargo", id);
    }
}