package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Vehicle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
@Repository
public class VehicleRepository extends GenericRepositoryImpl {

    public Vehicle createVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException {
        createDocument("Vehicle", vehicle.getIdVehicle(), vehicle);
        return vehicle;
    }

    public Vehicle getVehicleById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Vehicle", id, Vehicle.class);
    }

    public void updateVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Vehicle", vehicle.getIdVehicle(), vehicle);
    }

    public void removeDriver(String idVehicle, String idDriver, Vehicle vehicle) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Vehicle").document(idVehicle);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            List<String> driverList = (List<String>) document.get("driverList");
            if (driverList != null && driverList.contains(idDriver)) {
                driverList.remove(idDriver); // Chuyển đổi từ Integer sang int
                ApiFuture<WriteResult> updateFuture = docRef.update("driverList", driverList);
                updateFuture.get();
                System.out.println("Đã xóa idDriver khỏi danh sách driverList.");
            } else {
                System.out.println("idDriver không tồn tại trong danh sách driverList.");
            }
        } else {
            System.out.println("Không tìm thấy tài liệu với id: " + idVehicle);
        }
    }
    public void addDriver(String idVehicle, String idDriver, Vehicle vehicle) throws ExecutionException, InterruptedException, IllegalAccessException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Vehicle").document(idVehicle);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            List<String> driverList = (List<String>) document.get("driverList");
            if (driverList == null) {
                driverList = new ArrayList<>();
            }
            driverList.add(idDriver);
            ApiFuture<WriteResult> updateFuture = docRef.update("driverList", driverList);
            updateFuture.get();
        }
//        vehicle.addDriver(idDriver);
    }

    public void deleteVehicleById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Vehicle", id);
    }
}