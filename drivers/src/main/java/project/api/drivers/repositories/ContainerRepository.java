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

    public void removeCargo(String idVehicle, String idCargo) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference containerDocRef = db.collection("Container").document(idVehicle);
        DocumentReference cargoDocRef = db.collection("Cargo").document(idCargo);
        ApiFuture<DocumentSnapshot> containerFuture = containerDocRef.get();
        ApiFuture<DocumentSnapshot> cargoFuture = cargoDocRef.get();
        DocumentSnapshot containerDocument = containerFuture.get();
        DocumentSnapshot cargoDocument = cargoFuture.get();
        if (containerDocument.exists() && cargoDocument.exists()) {
            List<String> cargoList = (List<String>) containerDocument.get("cargoList");
            Double maxLoad = containerDocument.getDouble("maxLoad");
            Double currentLoad = containerDocument.getDouble("currentLoad");
            Double mass = cargoDocument.getDouble("mass");
            if(currentLoad == 0){
                System.out.println("con gi dau ma xoa");
                return;
            }
            if (cargoList != null && cargoList.contains(idCargo)) {
                cargoList.remove(idCargo);
                currentLoad = currentLoad - mass;
                ApiFuture<WriteResult> updateFuture = containerDocRef.update(
                        "cargoList", cargoList,
                        "currentLoad", currentLoad);
                updateFuture.get();
                System.out.println("Đã xóa idCargo khỏi danh sách cargoList.");
            } else {
                System.out.println("idCargo không tồn tại trong danh sách cargoList.");
            }
        } else {
            System.out.println("Không tìm thấy xe với id: " + idVehicle);
        }
    }
    public void addPassenger(String idVehicle, String idCargo) throws ExecutionException, InterruptedException, IllegalAccessException {
        System.out.println("1");
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference containerDocRef = db.collection("Container").document(idVehicle);
        DocumentReference cargoDocRef = db.collection("Cargo").document(idCargo);
        ApiFuture<DocumentSnapshot> containerFuture = containerDocRef.get();
        ApiFuture<DocumentSnapshot> cargoFuture = cargoDocRef.get();
        DocumentSnapshot containerDocument = containerFuture.get();
        DocumentSnapshot cargoDocument = cargoFuture.get();
        if (containerDocument.exists() && cargoDocument.exists()) {
            List<String> cargoList = (List<String>) containerDocument.get("cargoList");
            Double maxLoad = containerDocument.getDouble("maxLoad");
            Double currentLoad = containerDocument.getDouble("currentLoad");
            Double mass = cargoDocument.getDouble("mass");
            if (cargoList == null) {
                cargoList = new ArrayList<>();
            }
            if (!cargoList.contains(idCargo) && (currentLoad + mass <= maxLoad)){
                cargoList.add(idCargo);
                currentLoad = currentLoad + mass;
            }
            ApiFuture<WriteResult> updateFuture = containerDocRef.update(
                    "cargoList", cargoList,
                    "currentLoad", currentLoad);
            updateFuture.get();
        }
    }
}