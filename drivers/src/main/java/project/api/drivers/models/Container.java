package project.api.drivers.models;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Container {
    private String cargoType;
    private Date previousMaintenanceDate;
    private Date nextMaintenanceDate;
    private int currentLoad;
    private int maxLoad;
}
