package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Passenger;

import java.util.concurrent.ExecutionException;

@Repository
public class PassengerRepository {
    public String createPassenger(Passenger passenger) throws ExecutionException, InterruptedException {
        Firestore dbFileStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFileStore.collection("Passenger").document(passenger.getIDPassenger()).set(passenger);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public Passenger getPassengerById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Passenger").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Passenger.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }
    public String updatePassenger(Passenger passenger){
        return "";
    }
    public String deletePassengerById(String id){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> WriteResult = db.collection("Passenger").document(id).delete();
        return "delete" + id;
    }
}
