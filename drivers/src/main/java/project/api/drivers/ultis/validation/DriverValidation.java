package project.api.drivers.ultis.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import project.api.drivers.models.Driver;

import java.util.Set;

public class DriverValidation {

    private Validator validator;

    public DriverValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public String createValidation(Driver driver) {
        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        return formatViolations(violations);
    }

    public String getValidation(String id) {
        if (id == null || id.trim().isEmpty()) {
            return "ID is required";
        }
        return null;
    }

    public String updateValidation(Driver driver) {
        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        return formatViolations(violations);
    }

    public String deleteValidation(String id) {
        if (id == null || id.trim().isEmpty()) {
            return "ID is required";
        }
        return null;
    }

    private String formatViolations(Set<ConstraintViolation<Driver>> violations) {
        if (violations.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<Driver> violation : violations) {
            sb.append(violation.getMessage()).append("\n");
        }
        return sb.toString();
    }
}
