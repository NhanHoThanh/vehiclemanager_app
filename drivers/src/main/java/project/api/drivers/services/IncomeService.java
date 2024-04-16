package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.api.drivers.models.*;
import project.api.drivers.repositories.IncomeRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.UUID;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository ;
    public ResponseObject<Income> updateIncome(String id, Income income ) {
        ResponseObject<Income> responseObject = new ResponseObject<>();
        try {
            Income incomeUpdate = incomeRepository.getIncomeById(id);
            if (incomeUpdate!= null) {
                incomeRepository.updateIncome(income );
                responseObject.setStatus("success");
                responseObject.setMessage("Update Income successfully");
                responseObject.setData(incomeUpdate);
            } else {
                responseObject.setStatus("error");
                responseObject.setMessage("Route not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;

    }
    public ResponseObject<Income> createIncome(Income income) {
        ResponseObject<Income> responseObject = new ResponseObject<>();
        try {
            Income newIncome = incomeRepository.createIncome(income);
            responseObject.setStatus("success");
            responseObject.setMessage("Create Income successfully");
            responseObject.setData(newIncome);
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Income> getIncomeById(String id) {
        ResponseObject<Income> responseObject = new ResponseObject<>();
        try {
            Income income = incomeRepository.getIncomeById(id);
            if (income!= null) {
                responseObject.setStatus("success");
                responseObject.setMessage("Get Income by id successfully");
                responseObject.setData(income);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Income not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<Income> deleteIncome(String id) {
        ResponseObject<Income> responseObject = new ResponseObject<>();
        try {
            Income income = incomeRepository.getIncomeById(id);
            if (income  != null) {
                incomeRepository.deleteIncomeById(income.getIdIncome());
                responseObject.setStatus("success");
                responseObject.setMessage("Delete Income successfully");
                responseObject.setData(income);
            } else {
                responseObject.setStatus("fail");
                responseObject.setMessage("Income not found");
            }
        } catch (Exception e) {
            responseObject.setStatus("error");
//            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
    public ResponseObject<List<Income>> getAllIncome() {
        ResponseObject<List<Income>> responseObject = new ResponseObject<>();
        try {
            List<Income> income = incomeRepository.getAllDocuments("Income", Income.class);
            if (income.isEmpty()) {
                responseObject.setStatus("success");
                responseObject.setMessage("No Income found");
                return responseObject;
            }
            responseObject.setStatus("success");
            responseObject.setMessage("Get all Income successfully");
            responseObject.setData(income);
        } catch (Exception e) {
            responseObject.setStatus("error");
            //            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
        }
        return responseObject;
    }
//    public ResponseObject<Income> calculateIncomeFromSpecificCoach(ResponseEntity<Passenger[]> responseEntity ) {
//
//        ResponseObject<Income> responseObject = new ResponseObject<>();
//
//        Income income = new Income();
//        try {
//            Income newIncome = incomeRepository.createIncome(income);
//            responseObject.setStatus("success");
//            responseObject.setMessage("Create Income successfully");
//            responseObject.setData(newIncome);
//        } catch (Exception e) {
//            responseObject.setStatus("error");
////            responseObject.setMessage(STR."An error occurred: \{e.getMessage()}");
//        }
//        return responseObject;
//    }

    private double calculateCost(double distance) {
        double consumption = 20; // Giả sử mức tiêu thụ nhiên liệu là 10 lít/100km
        double costExtend = generateRandomCost(distance); // Tạo chi phí phát sinh ngẫu nhiên
        return (distance / 100) * consumption + costExtend;
    }
    private double generateRandomCost(double distance) {

        double minCostPerKm = 500000;
        double maxCostPerKm = 5000000;


        double costPerKm = (maxCostPerKm - minCostPerKm) * new Random().nextDouble() + minCostPerKm;


        double totalCostExtend = distance / 100 * costPerKm;

        return totalCostExtend;
    }


//    ResponseEntity<Passenger[]> responseEntity ,
//Passenger[] passengers

    public ResponseObject<Income> calculateIncomeFromSpecificCoach(Passenger[] passengers, Route route ) throws ExecutionException, InterruptedException {
        ResponseObject<Income> responseObject = new ResponseObject<>();


//
//        try {
//            if(responseEntity != null && responseEntity.getBody() != null) {
//                Passenger[] passengers = responseEntity.getBody();
                Double revenue = 0.0;
                for(Passenger passenger : passengers) {
                    revenue+=passenger.getCosPassenger();

                }
//                 Double cost =calculateCost(route.getDistance());
                Double cost =calculateCost(route.getDistance());

                Income income = new Income();
                income.setIdIncome(UUID.randomUUID().toString());
                income.setRevenue(revenue);
                income.setCost(cost);
                income.setProfit(income.set_profit());

//                Income newIncome = incomeRepository.createIncome(income);
//
//
//                responseObject.setStatus("success");
//                responseObject.setMessage("Create Income successfully");
//                responseObject.setData(newIncome);
////            } else {
////                responseObject.setStatus("error");
////                responseObject.setMessage("ResponseEntity is null or empty");
////            }
////        } catch (Exception e) {
////            responseObject.setStatus("error");
//////            responseObject.setMessage("An error occurred: " + e.getMessage());
////        }
//        return responseObject;
        return createIncome(income);
    }
    public ResponseObject<Income> calculateIncomeFromSpecificContainer(Cargo[] cargos, Route route ) throws ExecutionException, InterruptedException {
        ResponseObject<Income> responseObject = new ResponseObject<>();


//        try {
//            if(responseEntity != null && responseEntity.getBody() != null) {
//                Passenger[] passengers = responseEntity.getBody();
        Double revenue = 15000.0;
//        for(Passenger passenger : passengers) {
//            revenue+=passenger.getCosPassenger();
//
//        }
        Double distance = route.getDistance();
        Double mass =0.0;
        for(Cargo cargo : cargos ){
            Double massOfCargo = cargo.getMass();
            Double sizeOfCargo = cargo.getSize();
            if(sizeOfCargo!=0){
            mass = (sizeOfCargo/5000 +massOfCargo)/2;
        }
        else {
            mass = massOfCargo;
        }

        }
        mass = mass-100;
        if(mass>0){
            revenue += (mass/100)*5000*(distance/500);
        }
//                 Double cost =calculateCost(route.getDistance());
        Double cost =calculateCost(distance);

        Income income = new Income();
        income.setIdIncome(UUID.randomUUID().toString());
        income.setRevenue(revenue);
        income.setCost(cost);
        income.setProfit(income.set_profit());

//                Income newIncome = incomeRepository.createIncome(income);
//
//
//                responseObject.setStatus("success");
//                responseObject.setMessage("Create Income successfully");
//                responseObject.setData(newIncome);
////            } else {
////                responseObject.setStatus("error");
////                responseObject.setMessage("ResponseEntity is null or empty");
////            }
////        } catch (Exception e) {
////            responseObject.setStatus("error");
//////            responseObject.setMessage("An error occurred: " + e.getMessage());
////        }
//        return responseObject;
        return createIncome(income);
    }
}
