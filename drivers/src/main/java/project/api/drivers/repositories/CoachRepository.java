package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Coach;

import java.util.ArrayList;
import java.util.List;
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

    public void updateCoach(String id, Coach coach) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Coach", id, coach);
    }

    public void deleteCoachById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Coach", id);
    }

    public void removePassenger(String idVehicle, String idPassenger, Coach coach) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Coach").document(idVehicle);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            List<String> passengerList = (List<String>) document.get("passengerList");
            if (passengerList != null && passengerList.contains(idPassenger)) {
                passengerList.remove(idPassenger);
                ApiFuture<WriteResult> updateFuture = docRef.update("passengerList", passengerList);
                updateFuture.get();
                System.out.println("Đã xóa idpassenger khỏi danh sách passengerList.");
            } else {
                System.out.println("idpassenger không tồn tại trong danh sách passengerList.");
            }
        } else {
            System.out.println("Không tìm thấy tài liệu với id: " + idVehicle);
        }
    }
    public void addPassenger(String idVehicle, String idPassenger, Coach coach) throws ExecutionException, InterruptedException, IllegalAccessException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Coach").document(idVehicle);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            List<String> passengerList = (List<String>) document.get("passengerList");
            if (passengerList == null) {
                passengerList = new ArrayList<>();
            }
            passengerList.add(idPassenger);
            ApiFuture<WriteResult> updateFuture = docRef.update("passengerList", passengerList);
            updateFuture.get();
        }
    }
}