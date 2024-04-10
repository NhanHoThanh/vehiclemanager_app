package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import project.api.drivers.models.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class RouteRepository {
    private final Firestore firestore;
    private final CollectionReference routeCollection;

    public RouteRepository() {
        this.firestore = FirestoreClient.getFirestore();
        this.routeCollection = firestore.collection("routes");
    }

    public Route getRouteById(int id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = routeCollection.document(String.valueOf(id));
        DocumentSnapshot document = docRef.get().get();
        if (document.exists()) {
            return document.toObject(Route.class);
        } else {
            return null;
        }
    }

    public void deleteRouteById(int id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = routeCollection.document(String.valueOf(id));
        ApiFuture<WriteResult> future = docRef.delete();
        future.get();
    }

    public void createRoute(Route route) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentReference> future = routeCollection.add(route);
        future.get();
    }

    public void updateRoute(Route route) throws ExecutionException, InterruptedException {
        DocumentReference docRef = routeCollection.document(String.valueOf(route.getIdRoute()));
        ApiFuture<WriteResult> future = docRef.set(route);
        future.get();
    }

    public List<Route> getAllRoutes() throws ExecutionException, InterruptedException {
        List<Route> routes = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshot = routeCollection.get();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            routes.add(document.toObject(Route.class));
        }
        return routes;
    }
}
