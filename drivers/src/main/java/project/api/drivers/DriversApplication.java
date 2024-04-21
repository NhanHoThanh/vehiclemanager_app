package project.api.drivers;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@SpringBootApplication

public class DriversApplication {
	public DriversApplication() {
	}

    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {


		FileInputStream serviceAccount =
				new FileInputStream("drivers/src/main/resources/serviceAccount.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
		SpringApplication.run(DriversApplication.class, args);
	}

}

