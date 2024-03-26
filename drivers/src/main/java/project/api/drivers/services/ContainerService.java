package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.repositories.ContainerRepository;
import project.api.drivers.models.Container;
import java.util.concurrent.ExecutionException;

@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;

    public Container getContainerById(String id) throws ExecutionException, InterruptedException {
        return containerRepository.getContainerById(id);
    }
}
