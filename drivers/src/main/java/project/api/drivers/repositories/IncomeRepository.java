package project.api.drivers.repositories;

import org.springframework.stereotype.Repository;
import project.api.drivers.models.Income;

import java.util.concurrent.ExecutionException;
@Repository
public class IncomeRepository extends GenericRepositoryImpl  {
    public Income createIncome(Income income) throws ExecutionException, InterruptedException {
        createDocument("Income", income.getIdIncome(), income);
        return income;
    }

    public Income getIncomeById(String id) throws ExecutionException, InterruptedException {
        return getDocument("Income", id, Income.class);
    }

    public void updateIncome(Income income) throws ExecutionException, InterruptedException, IllegalAccessException {
        updateDocument("Income", income.getIdIncome(), income);
    }

    public void deleteIncomeById(String id) throws ExecutionException, InterruptedException {
        deleteDocument("Income", id);
    }
}
