package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Driver;
import java.util.concurrent.ExecutionException;
import project.api.drivers.models.Cargo;

@Repository
public class CargoRepository {
    public String createCargo(Cargo cargo) throws ExecutionException, InterruptedException {
        Firestore dbFileStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFileStore.collection("Container").document(cargo.getIDCargo()).set(cargo);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public Cargo getCargoById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Container").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Cargo.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }
    public String updateCargo(Cargo cargo){
        return "";
    }
    public String deleteCargoById(String id){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> WriteResult = db.collection("Container").document(id).delete();
        return "delete" + id;
    }
}
