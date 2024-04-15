package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Coach;
import project.api.drivers.models.CoachVehicle;
import project.api.drivers.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Repository
public class CoachRepository extends GenericRepositoryImpl {

    public CoachVehicle createCoach(CoachVehicle coachVehicle) throws ExecutionException, InterruptedException {
        Coach coach = new Coach(coachVehicle.getNumberOfSeats(), coachVehicle.getNumberOfPassenger(), coachVehicle.getPreviousMaintenanceDate(), coachVehicle.getNextMaintenanceDate(), coachVehicle.getEmptySeat(), coachVehicle.getPassengerList());
        Vehicle vehicle = new Vehicle(coachVehicle.getIdVehicle(), coachVehicle.getDriverList(), coachVehicle.getCapacity(), coachVehicle.getFuelType(), coachVehicle.getStatus(), coachVehicle.getRoute(), coachVehicle.getVehicleType(), coachVehicle.getTimeStart(), coachVehicle.getTimeEnd(), coachVehicle.getDestination(), coachVehicle.getDeparture());
        createDocument("Coach", coachVehicle.getIdVehicle(), coach);
        createDocument("Vehicle", coach.getIdVehicle(), vehicle);
        return coachVehicle;
    }

    public Coach getCoachById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Coach", id, Coach.class);
    }

    public void updateCoach(String id, Coach coach) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Coach", id, coach);
    }

    public void deleteCoachById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Coach", id);
    }

    public void removePassenger(String idVehicle, String idPassenger) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference coachDocRef = db.collection("Coach").document(idVehicle);
        DocumentReference passengerDocRef = db.collection("Passenger").document(idPassenger);
        ApiFuture<DocumentSnapshot> coachFuture = coachDocRef.get();
        ApiFuture<DocumentSnapshot> passengerFuture = passengerDocRef.get();
        DocumentSnapshot document = coachFuture.get();
        DocumentSnapshot passengerDocument = passengerFuture.get();
        if (passengerDocument.exists() && document.exists()) {
            String seatingPosition = passengerDocument.getString("seatingPosition");
            List<String> passengerList = (List<String>) document.get("passengerList");
            List<String> emptySeat = (List<String>) document.get("emptySeat");
            Long numberOfPassenger = document.getLong("numberOfPassenger");
            if (passengerList == null) {
                System.out.println("xe het khach roi");
                return;
            }
            if (emptySeat == null) {
                emptySeat = new ArrayList<>();
            }
            if(!emptySeat.contains(seatingPosition) && passengerList.contains(idPassenger)){
                passengerList.remove(idPassenger);
                emptySeat.add(seatingPosition);
                numberOfPassenger = (long) passengerList.size();
            }
            else{
                System.out.println("khach xuong xe roi");
            }
            ApiFuture<WriteResult> updateFuture = coachDocRef.update(
                    "emptySeat", emptySeat,
                    "passengerList", passengerList,
                    "numberOfPassenger", numberOfPassenger.intValue()
            );
            updateFuture.get();
        }
    }
    public void addPassenger(String idVehicle, String idPassenger) throws ExecutionException, InterruptedException, IllegalAccessException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference coachDocRef = db.collection("Coach").document(idVehicle);
        DocumentReference passengerDocRef = db.collection("Passenger").document(idPassenger);
        ApiFuture<DocumentSnapshot> coachFuture = coachDocRef.get();
        ApiFuture<DocumentSnapshot> passengerFuture = passengerDocRef.get();
        DocumentSnapshot document = coachFuture.get();
        DocumentSnapshot passengerDocument = passengerFuture.get();
        if (passengerDocument.exists() && document.exists()) {
            String seatingPosition = passengerDocument.getString("seatingPosition");
            List<String> passengerList = (List<String>) document.get("passengerList");
            List<String> emptySeat = (List<String>) document.get("emptySeat");
            Long numberOfPassenger = document.getLong("numberOfPassenger");
            if (passengerList == null) {
                passengerList = new ArrayList<>();
            }
            if (emptySeat == null) {
                System.out.println("ko co ghe trong");
                return;
            }
            if(emptySeat.contains(seatingPosition) && !passengerList.contains(idPassenger)){
                passengerList.add(idPassenger);
                emptySeat.remove(seatingPosition);
                numberOfPassenger = (long) passengerList.size();
            }
            else{
                System.out.println("khach len xe roi");
            }
            ApiFuture<WriteResult> updateFuture = coachDocRef.update(
                    "emptySeat", emptySeat,
                    "passengerList", passengerList,
                    "numberOfPassenger", numberOfPassenger.intValue()
            );
            updateFuture.get();
        }
    }
}