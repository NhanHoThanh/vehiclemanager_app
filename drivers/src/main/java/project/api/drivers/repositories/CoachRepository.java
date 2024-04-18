package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Coach;
import project.api.drivers.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Repository
public class CoachRepository extends GenericRepositoryImpl {

    public Coach createCoach(Coach coach) throws ExecutionException, InterruptedException {
        Vehicle vehicle = new Vehicle(coach.getIdVehicle(), coach.getDriverList(), coach.getHistoryRouteList(), coach.getHistoryIncomeList(), coach.getTimeStartList(), coach.getTimeEndList(), coach.getCapacity(), coach.getFuelType(), coach.getStatus(), coach.getRoute(), coach.getVehicleType(), coach.getTimeStart(), coach.getTimeEnd(), coach.getDestination(), coach.getDeparture());
        createDocument("Vehicle", coach.getIdVehicle(), vehicle);
        createDocument("Coach", coach.getIdVehicle(), coach);
        return coach;
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
    public void addPassenger(String idVehicle, String idPassenger, Boolean responseRoute) throws ExecutionException, InterruptedException, IllegalAccessException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference coachDocRef = db.collection("Coach").document(idVehicle);
        DocumentReference passengerDocRef = db.collection("Passenger").document(idPassenger);
        ApiFuture<DocumentSnapshot> coachFuture = coachDocRef.get();
        ApiFuture<DocumentSnapshot> passengerFuture = passengerDocRef.get();
        DocumentSnapshot document = coachFuture.get();
        DocumentSnapshot passengerDocument = passengerFuture.get();
        if (passengerDocument.exists() && document.exists() && responseRoute) {
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
    public List<Coach> getCoachByAttributes(Coach coach) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionRef = db.collection("Coach");

        List<Coach> coaches = new ArrayList<>();

        Query query = collectionRef;

        if (coach != null) {
            if (coach.getDeparture() != null) {
                query = query.whereEqualTo("departure", coach.getDeparture());
            }
            if (coach.getDestination() != null) {
                query = query.whereEqualTo("destination", coach.getDestination());
            }
            if (coach.getTimeStart() != null) {
                query = query.whereEqualTo("timeStart", coach.getTimeStart());
            }
        }

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Coach retrievedCoach = document.toObject(Coach.class);
            coaches.add(retrievedCoach);
        }
        return coaches;
    }
}