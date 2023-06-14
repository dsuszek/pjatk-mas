package MP3.abstract_class;

import MP3.ObjectPlus;

import java.util.UUID;

public abstract class Vehicle extends ObjectPlus {
    private UUID id;
    private String brand;
    private String model;
    private String vehicleRegistrationNumber;
    public abstract double calculateRentalPricePerDay();

    public Vehicle(String brand, String model, String vehicleRegistrationNumber) {
        setId();
        setBrand(brand);
        setModel(model);
        setVehicleRegistrationNumber(vehicleRegistrationNumber);

        // add to extent
        addToExtent();
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

}
