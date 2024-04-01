package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Coach;

import java.util.concurrent.ExecutionException;
@Repository
public class CoachRepository extends GenericRepositoryImpl {

    public Coach createCoach(Coach coach) throws ExecutionException, InterruptedException {
        createDocument("Coach", coach.getIdVehicle(), coach);
        return coach;
    }

    public Coach getCoachById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Coach", id, Coach.class);
    }

    public void updateCoach(Coach coach) throws ExecutionException, InterruptedException {
        updateDocument("Coach", coach.getIdVehicle(), coach);
    }

    public void deleteCoachById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Coach", id);
    }
}