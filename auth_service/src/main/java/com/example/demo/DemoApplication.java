package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.*;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoApplication {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		FileInputStream serviceAccount =
				new FileInputStream("src/main/resources/serviceAccount.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(DemoApplication.class, args);
	}
}
