package project.api.drivers.ultis.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateDriverVehicle {
    @NotBlank(message = "Vehicle ID is required")
    private String vehicleId;
    @NotBlank(message = "Vehicle Type is required")
    @Pattern(regexp = "^(Container|Coach)$", message = "Vehicle Type must be either 'Truck' or 'Coach'")
    private String vehicleType;

    public UpdateDriverVehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public UpdateDriverVehicle() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
