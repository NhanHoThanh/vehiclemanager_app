package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Vehicle;

import java.util.concurrent.ExecutionException;
@Repository
public class VehicleRepository {
    public String createVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException {
        Firestore dbFileStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFileStore.collection("Vehicle").document(vehicle.getIdVehicle()).set(vehicle);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Vehicle getVehicleById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference vehicleRef = db.collection("Vehicle").document(id);
        ApiFuture<DocumentSnapshot> vehicleFuture = vehicleRef.get();
        DocumentSnapshot vehicleDocument = vehicleFuture.get();
        if (vehicleDocument.exists()) {
            System.out.println(vehicleDocument);
            return vehicleDocument.toObject(Vehicle.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }
    public String updateCoach(Vehicle vehicle){
        return "";
    }
    public String deleteVehicleById(String id){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> WriteResult = db.collection("Vehicle").document(id).delete();
        return "delete" + id;
    }
}
