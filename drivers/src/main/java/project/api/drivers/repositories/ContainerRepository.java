package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Container;

import java.util.concurrent.ExecutionException;

@Repository
public class ContainerRepository {
    public String createContainer(@org.jetbrains.annotations.NotNull Container container) throws ExecutionException, InterruptedException {
    Firestore dbFileStore = FirestoreClient.getFirestore();
    ApiFuture<WriteResult> collectionApiFuture = dbFileStore.collection("Container").document(container.getIdVehicle()).set(container);
    return collectionApiFuture.get().getUpdateTime().toString();
}
    public Container getContainerById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Container").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Container.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }
    public String updateContainer(Container container){
        return "";
    }
    public String deleteContainerById(String id){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> WriteResult = db.collection("Container").document(id).delete();
        return "delete" + id;
    }
}
