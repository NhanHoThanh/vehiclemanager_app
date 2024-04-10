package project.api.drivers.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import project.api.drivers.models.Income;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class IncomeRepository {

    private final Firestore firestore;
    private final CollectionReference incomeCollection;

    public IncomeRepository() {
        this.firestore = FirestoreClient.getFirestore();
        this.incomeCollection = firestore.collection("income");
    }

    public Income getIncomeById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = incomeCollection.document(id);
        ApiFuture<DocumentSnapshot> querySnapshot = docRef.get();
        DocumentSnapshot document = querySnapshot.get();
        if (document.exists()) {
            return document.toObject(Income.class);
        } else {
            return null;
        }
    }

    public List<Income> getAllIncome() throws ExecutionException, InterruptedException {
        List<Income> incomes = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshot = incomeCollection.get();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            incomes.add(document.toObject(Income.class));
        }
        return incomes;
    }

    public void updateIncome(Income income) throws ExecutionException, InterruptedException {
        DocumentReference docRef = incomeCollection.document(income.getId());
        ApiFuture<WriteResult> future = docRef.set(income);
        future.get();
    }

    public void deleteIncome(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = incomeCollection.document(id);
        ApiFuture<WriteResult> future = docRef.delete();
        future.get();
    }

    public double calculateRevenue(double distance, double volume, double mass, Map<Double, Integer> listTicketCost) {
        return new Income().set_Revenue(distance, volume, mass, listTicketCost);
    }

    public double calculateCost(double distance, double costExtend, double consumption) {
        return new Income().set_Cost(distance, costExtend, consumption);
    }

    public double calculateProfit(double distance, double cost, double revenue) {
        return new Income(cost, revenue, 0).set_profit();
    }
}
