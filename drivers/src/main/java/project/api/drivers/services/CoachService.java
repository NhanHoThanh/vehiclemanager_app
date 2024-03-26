package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Coach;
import project.api.drivers.repositories.CargoRepository;
import project.api.drivers.repositories.CoachRepository;

import java.util.concurrent.ExecutionException;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    public Coach getCoachById(String id) throws ExecutionException, InterruptedException {
        return coachRepository.getCoachById(id);
    }
}
