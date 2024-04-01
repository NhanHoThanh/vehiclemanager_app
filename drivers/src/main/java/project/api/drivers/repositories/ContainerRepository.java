package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Container;

import java.util.concurrent.ExecutionException;

@Repository
public class ContainerRepository extends GenericRepositoryImpl {

    public Container createContainer(Container container) throws ExecutionException, InterruptedException {
        createDocument("Container", container.getIdVehicle(), container);
        return container;
    }

    public Container getContainerById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Container", id, Container.class);
    }

    public void updateContainer(Container container) throws ExecutionException, InterruptedException {
        updateDocument("Container", container.getIdVehicle(), container);
    }

    public void deleteContainerById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Container", id);
    }
}