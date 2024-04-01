package project.api.drivers.services;

import project.api.drivers.models.Income;
import project.api.drivers.repositories.IncomeRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class IncomeService {
    private IncomeRepository incomeRepository = new IncomeRepository();

    public Income getIncomeById(String id) throws ExecutionException, InterruptedException {
        return incomeRepository.getIncomeById(id);
    }

    public List<Income> getAllIncome() throws ExecutionException, InterruptedException {
        return incomeRepository.getAllIncome();
    }

    public void updateIncome(Income income) throws ExecutionException, InterruptedException {
        incomeRepository.updateIncome(income);
    }

    public void deleteIncome(String id) throws ExecutionException, InterruptedException {
        incomeRepository.deleteIncome(id);
    }

    public double calculateRevenue(double distance, double volume, double mass, Map<Double, Integer> listTicketCost) {
        return incomeRepository.calculateRevenue(distance, volume, mass, listTicketCost);
    }

    public double calculateCost(double distance, double costExtend, double consumption) {
        return incomeRepository.calculateCost(distance, costExtend, consumption);
    }

    public double calculateProfit(double distance, double cost, double revenue) {
        return incomeRepository.calculateProfit(distance, cost, revenue);
    }

}
