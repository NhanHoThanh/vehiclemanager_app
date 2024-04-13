package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ContainerRepository extends GenericRepositoryImpl {

    public Container createContainer(Container container) throws ExecutionException, InterruptedException {
        createDocument("Container", container.getIdVehicle(), container);
        return container;
    }

    public Container getContainerById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Container", id, Container.class);
    }

    public void updateContainer(Container container) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Container", container.getIdVehicle(), container);
    }

    public void deleteContainerById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Container", id);
    }

    public void removeCargo(String idVehicle, String idCargo, Container container) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Container").document(idVehicle);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            List<String> cargoList = (List<String>) document.get("cargoList");
            if (cargoList != null && cargoList.contains(idCargo)) {
                cargoList.remove(idCargo); // Chuyển đổi từ Integer sang int
                ApiFuture<WriteResult> updateFuture = docRef.update("cargoList", cargoList);
                updateFuture.get();
                System.out.println("Đã xóa idCargo khỏi danh sách cargoList.");
            } else {
                System.out.println("idCargo không tồn tại trong danh sách cargoList.");
            }
        } else {
            System.out.println("Không tìm thấy tài liệu với id: " + idVehicle);
        }
    }
    public void addPassenger(String idVehicle, String idCargo, Container container) throws ExecutionException, InterruptedException, IllegalAccessException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Container").document(idVehicle);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            List<String> cargoList = (List<String>) document.get("cargoList");
            if (cargoList == null) {
                cargoList = new ArrayList<>();
            }
            cargoList.add(idCargo);
            ApiFuture<WriteResult> updateFuture = docRef.update("cargoList", cargoList);
            updateFuture.get();
        }
    }
}