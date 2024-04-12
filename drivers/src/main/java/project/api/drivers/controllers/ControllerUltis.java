package project.api.drivers.controllers;

import org.jetbrains.annotations.NotNull;
import project.api.drivers.models.CreateDriver;
import project.api.drivers.models.Driver;
import project.api.drivers.models.UpdateDriver;

public class ControllerUltis {
    @NotNull
    public static Driver getDriver(UpdateDriver updateDriver) {
        Driver driver = new Driver();

        if (updateDriver.getAddress() != null) {
            driver.setAddress(updateDriver.getAddress());
        }
        if (updateDriver.getCccd() != null) {
            driver.setCccd(updateDriver.getCccd());
        }
        if (updateDriver.getLicense() != null) {
            driver.setLicense(updateDriver.getLicense());
        }
        if (updateDriver.getName() != null) {
            driver.setName(updateDriver.getName());
        }
        if (updateDriver.getPhone_number() != null) {
            driver.setPhone_number(updateDriver.getPhone_number());
        }
        if (updateDriver.getEmail() != null) {
            driver.setEmail(updateDriver.getEmail());
        }
        return driver;
    }
    @NotNull
    public static Driver getDriver(CreateDriver createDriver) {
        Driver driver = new Driver();

        if (createDriver.getAddress() != null) {
            driver.setAddress(createDriver.getAddress());
        }
        if (createDriver.getCccd() != null) {
            driver.setCccd(createDriver.getCccd());
        }
        if (createDriver.getLicense() != null) {
            driver.setLicense(createDriver.getLicense());
        }
        if (createDriver.getName() != null) {
            driver.setName(createDriver.getName());
        }
        if (createDriver.getPhone_number() != null) {
            driver.setPhone_number(createDriver.getPhone_number());
        }
        if (createDriver.getEmail() != null) {
            driver.setEmail(createDriver.getEmail());
        }
        return driver;
    }
}
