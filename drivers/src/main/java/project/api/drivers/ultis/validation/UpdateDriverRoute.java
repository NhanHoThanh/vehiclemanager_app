package project.api.drivers.ultis.validation;

import jakarta.validation.constraints.NotBlank;

public class UpdateDriverRoute {
    @NotBlank(message = "Route ID is required")
    private String routeId;

    public UpdateDriverRoute(String routeId) {
        this.routeId = routeId;
    }

    public UpdateDriverRoute() {
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
}
