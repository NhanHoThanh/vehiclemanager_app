package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Coach;

import java.util.concurrent.ExecutionException;
@Repository
public class CoachRepository {
    public String createCoach(Coach coach) throws ExecutionException, InterruptedException {
        Firestore dbFileStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFileStore.collection("Coach").document(coach.getIdVehicle()).set(coach);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Coach getCoachById(String id) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference coachRef = db.collection("Coach").document(id);
        ApiFuture<DocumentSnapshot> coachFuture = coachRef.get();
        DocumentSnapshot coachDocument = coachFuture.get();
        if (coachDocument.exists()) {
            System.out.println(Coach.class);
            return coachDocument.toObject(Coach.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }
    public String updateCoach(Coach coach){
        return "";
    }
    public String deleteCoachById(String id){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> WriteResult = db.collection("Coach").document(id).delete();
        return "delete" + id;
    }
}
