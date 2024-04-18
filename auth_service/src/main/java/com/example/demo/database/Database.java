// package com.example.demo.database;

// import com.example.demo.repositories.VehicleRepository;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.example.demo.models.Vehicle;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class Database {
//     private static final Logger logger = LoggerFactory.getLogger(Database.class);
//     @Bean
//     CommandLineRunner initDatabase(VehicleRepository vehiclesRepository) {
//         return new CommandLineRunner() {
//             @Override
//             public void run(String ...args) throws Exception {
//                 Vehicle v1 = new Vehicle("72C - 88767", "Thaco", "truck", 5000F);
//                 Vehicle v2 = new Vehicle("72B - 34767", "Hyundai", "coach", 6475F, 29);
//                 logger.info("Inserted data: " + vehiclesRepository.save(v1));
//                 logger.info("Inserted data: " + vehiclesRepository.save(v2));
//             }
//         };
//     }
// }
