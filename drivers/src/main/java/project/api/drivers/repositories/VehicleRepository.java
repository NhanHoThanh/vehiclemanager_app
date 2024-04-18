package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Coach;
import project.api.drivers.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.UUID;
@Repository
public class VehicleRepository extends GenericRepositoryImpl {

    public Vehicle createVehicle(Vehicle vehicle) throws ExecutionException, InterruptedException {
        vehicle.setIdVehicle(UUID.randomUUID().toString());
//        vehicle.addRoute(vehicle.getRoute());
        createDocument("Vehicle", vehicle.getIdVehicle(), vehicle);
        return vehicle;
    }

    public Vehicle getVehicleById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Vehicle", id, Vehicle.class);
    }

    public List<Vehicle> getVehicleRoute(Map<String, String> departure, Map<String, String> destination) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference vehiclesRef = db.collection("Vehicle");
        List<Vehicle> matchingVehicles = new ArrayList<>();

        Query query = vehiclesRef.whereEqualTo("departure", departure).whereEqualTo("destination", destination);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Vehicle foundVehicle = document.toObject(Vehicle.class);
            matchingVehicles.add(foundVehicle);
        }
        System.out.println(matchingVehicles.size());
        return matchingVehicles;
    }

    public List<Vehicle> getDocumentsByMultipleAttributes(Map<String, String> departureAttributes, Map<String, String> destinationAttributes) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionRef = db.collection("Vehicle");
        Query query = collectionRef;

        for (Map.Entry<String, String> departureAttribute : departureAttributes.entrySet()) {
            query = query.whereEqualTo(departureAttribute.getKey(), departureAttribute.getValue());
        }

        for (Map.Entry<String, String> destinationAttribute : destinationAttributes.entrySet()) {
            query = query.whereEqualTo(destinationAttribute.getKey(), destinationAttribute.getValue());
        }

        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot = future.get();

        if (!querySnapshot.isEmpty()) {
            List<Vehicle> vehicles = new ArrayList<>();

            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                vehicles.add(document.toObject(Vehicle.class));
            }

            return vehicles;
        } else {
            return null;
        }
    }


    public void updateVehicle(String id, Vehicle vehicle) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Vehicle", id, vehicle);
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
    public List<Vehicle> getVehicleByAttributes(Vehicle vehicle) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionRef = db.collection("Coach");

        List<Vehicle> listVehicle = new ArrayList<>();

        Query query = collectionRef;
        System.out.println(vehicle != null);
        if (vehicle != null) {
            if (vehicle.getDeparture() != null) {
                query = query.whereEqualTo("departure", vehicle.getDeparture());
            }
            if (vehicle.getDestination() != null) {
                query = query.whereEqualTo("destination", vehicle.getDestination());
            }
            if (vehicle.getTimeStart() != null) {
                query = query.whereEqualTo("timeStart", vehicle.getTimeStart());
            }
            if (vehicle.getVehicleType() != null) {
                query = query.whereEqualTo("vehicleType", vehicle.getVehicleType());
            }
        }

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            Vehicle retrievedVehicle = document.toObject(Vehicle.class);
            listVehicle.add(retrievedVehicle);
        }
        return listVehicle;
    }
}