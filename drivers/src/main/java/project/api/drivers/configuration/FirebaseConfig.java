//package project.api.drivers.configuration;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//public class FirebaseConfig {
//
//
//    public DatabaseReference firebaseDatabase() {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.setPersistenceEnabled(true);
//
//        return firebaseDatabase.getReference("https://javaspringboot-78e20-default-rtdb.asia-southeast1.firebasedatabase.app/");
//    }
//}