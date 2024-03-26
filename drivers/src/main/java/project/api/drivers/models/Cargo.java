package project.api.drivers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends Vehicle{
    private String IDCargo;
    private int mass;
    private String receiver;
    private String sender;
    private String sendingPlace;
    private String recipients;
    private String phoneNumber;
    private String nameCargo;
}
