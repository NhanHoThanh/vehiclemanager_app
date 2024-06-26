package project.api.drivers.repositories;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface GenericRepository {
    public boolean checkValueExits(String collectionName, String attributeName, String attributeValue) throws ExecutionException, InterruptedException;

    <T> void createDocument(String collectionName, String documentId, T newObject) throws ExecutionException, InterruptedException;

    <T> List<T> getAllDocuments(String collectionName, Class<T> clazz) throws ExecutionException, InterruptedException;

    <T> T getDocument(String collectionName, String documentId, Class<T> clazz) throws ExecutionException, InterruptedException;

    <T> List<T> getDocumentsByAttribute(String collectionName, String attributeName, String attributeValue, Class<T> clazz) throws ExecutionException, InterruptedException;

    <T> List<T> getDocumentsByMultipleAttributes(String collectionName, Map<String, String > attributes, Class<T> clazz) throws ExecutionException, InterruptedException;

    void deleteDocument(String collectionName, String documentId) throws ExecutionException, InterruptedException;

    <T> void updateDocument(String collectionName, String documentId, T updatedObject) throws ExecutionException, InterruptedException, IllegalAccessException;
}