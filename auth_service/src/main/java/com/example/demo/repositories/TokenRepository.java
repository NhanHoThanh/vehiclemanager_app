// package com.example.demo.repositories;

// import com.example.demo.models.Token;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import java.util.List;
// import java.util.Optional;

// // public interface TokenRepository extends JpaRepository<Token, Integer> {


// //     @Query("""
// // select t from Token t inner join User u on t.user.id = u.id
// // where t.user.id = :userId and t.loggedOut = false
// // """)
// //     List<Token> findAllTokensByUser(Integer userId);

// //     Optional<Token> findByToken(String token);
// // }

// public interface TokenRepository extends JpaRepository<Token, Integer> {

//     @Query("""
//         SELECT t FROM Token t WHERE t.user.id = :userId AND t.loggedOut = false
//     """)
//     List<Token> findAllTokensByUserId(Integer userId);

//     Optional<Token> findByToken(String token);
// }

package com.example.demo.repositories;

import com.example.demo.models.Token;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class TokenRepository {

    private final Firestore db = FirestoreClient.getFirestore();

    public List<Token> findAllTokensByUserId(Integer userId) throws ExecutionException, InterruptedException {
        CollectionReference tokensRef = db.collection("tokens");
        ApiFuture<QuerySnapshot> future = tokensRef.whereEqualTo("userId", userId).whereEqualTo("loggedOut", false).get();
        QuerySnapshot querySnapshot = future.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments(); // Change to QueryDocumentSnapshot
        return documents.stream()
                .map(snapshot -> snapshot.toObject(Token.class))
                .collect(Collectors.toList());
    }

    public Optional<Token> findByToken(String token) throws ExecutionException, InterruptedException {
        CollectionReference tokensRef = db.collection("tokens");
        ApiFuture<QuerySnapshot> future = tokensRef.whereEqualTo("token", token).get();
        QuerySnapshot querySnapshot = future.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments(); // Change to DocumentSnapshot
        if (documents.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(documents.get(0).toObject(Token.class));
    }

    public void save(Token token) throws ExecutionException, InterruptedException {
        db.collection("tokens").add(token);
    }

    public void saveAll(List<Token> tokens) throws ExecutionException, InterruptedException {
        CollectionReference tokensRef = db.collection("tokens");
        for (Token token : tokens) {
            tokensRef.add(token);
        }
    }
}

