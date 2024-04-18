package com.example.demo.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class GenericRepositoryImpl implements GenericRepository {

    public <T> void createDocument(String collectionName, String documentId, T newObject) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> future = docRef.set(newObject);
        future.get();
    }
    
    public <T> List<T> getAllDocuments(String collectionName, Class<T> clazz) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionRef = db.collection(collectionName);
        ApiFuture<QuerySnapshot> future = collectionRef.get();
        QuerySnapshot querySnapshot = future.get();
        if (!querySnapshot.isEmpty()) {
            List<T> documents = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                documents.add(document.toObject(clazz));
            }
            return documents;
        } else {
            return null;
        }
    }

    public <T> T getDocument(String collectionName, String documentId, Class<T> clazz) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(clazz);
        } else {
            return null;
        }
    }
    public <T> List<T> getDocumentsByAttribute(String collectionName, String attributeName, String attributeValue, Class<T> clazz) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionRef = db.collection(collectionName);
        ApiFuture<QuerySnapshot> future = collectionRef.whereEqualTo(attributeName, attributeValue).get();
        QuerySnapshot querySnapshot = future.get();
        if (!querySnapshot.isEmpty()) {
            List<T> documents = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                documents.add(document.toObject(clazz));
            }
            return documents;
        } else {
            return new ArrayList<>();
        }
    }
    public <T> List<T> getDocumentsByMultipleAttributes(String collectionName, Map<String, String> attributes, Class<T> clazz) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionRef = db.collection(collectionName);
        Query query = collectionRef;
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            query = query.whereEqualTo(attribute.getKey(), attribute.getValue());
        }
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot = future.get();
        if (!querySnapshot.isEmpty()) {
            List<T> documents = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                documents.add(document.toObject(clazz));
            }
            return documents;
        } else {
            return null;
        }
    }
    public void deleteDocument(String collectionName, String documentId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> future = docRef.delete();
        future.get();
    }

    public <T> void updateDocument(String collectionName, String documentId, T updatedObject) throws ExecutionException, InterruptedException, IllegalAccessException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            Map<String, Object> updatedFields = new HashMap<>();
            for (Field field : updatedObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(updatedObject) != null) {
                    updatedFields.put(field.getName(), field.get(updatedObject));
                }
            }
            ApiFuture<WriteResult> updateFuture = docRef.update(updatedFields);
            updateFuture.get();
        }
    }
}
