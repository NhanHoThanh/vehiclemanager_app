package project.api.drivers.ultis.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateDriver {

    private String address;

    @Pattern(regexp = "\\d{12}", message = "CCCD must be exactly 12 digits")
    private String cccd;

    @Pattern(regexp = "^(A1|A2|B1|B2|C1|C2)$", message = "License must be one of the following: A1, A2, B1, B2, C1, C2")
    private String license;

    private String name;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
    private String phone_number;

    @Email(message = "Email is invalid")
    private String email;

    public UpdateDriver(String address, String cccd, String license, String name, String phone_number, String email) {
        this.address = address;
        this.cccd = cccd;
        this.license = license;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public UpdateDriver() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
