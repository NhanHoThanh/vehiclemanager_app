package project.api.drivers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.api.drivers.models.Income;
import project.api.drivers.services.IncomeService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/income")


public class IncomeController {
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/{id}")
    public Income getIncomeById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return incomeService.getIncomeById(id);
    }

    @GetMapping("/all")
    public List<Income> getAllIncome() throws ExecutionException, InterruptedException {
        return incomeService.getAllIncome();
    }

    @PostMapping("/create")
    public void createIncome(@RequestBody Income income) throws ExecutionException, InterruptedException {
        incomeService.updateIncome(income);
    }

    @PutMapping("/update")
    public void updateIncome(@RequestBody Income income) throws ExecutionException, InterruptedException {
        incomeService.updateIncome(income);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable String id) throws ExecutionException, InterruptedException {
        incomeService.deleteIncome(id);
    }
}
