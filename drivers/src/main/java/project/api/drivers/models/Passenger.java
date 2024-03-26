package project.api.drivers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    private String IDPassenger;
    private String destination;
    private String departure;
    private String name;
    private int seatingPosition;
}

