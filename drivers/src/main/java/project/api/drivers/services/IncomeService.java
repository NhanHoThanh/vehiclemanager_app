package project.api.drivers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.api.drivers.models.Income;
import project.api.drivers.repositories.IncomeRepository;
import project.api.drivers.ultis.ResponseObject;

import java.util.List;
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
    public ResponseObject<Income> createRoute(Income income) {
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
}
