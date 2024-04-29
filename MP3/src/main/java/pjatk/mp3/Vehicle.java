package pjatk.mp3;

import java.util.UUID;

public abstract class Vehicle extends ObjectPlus {
    // Wszystkie atrybuty klasy Vehicle muszą być widoczne dla klas dziedziczących po niej - używamy ograniczenia protected.
    protected UUID id;
    protected Brand brand;
    protected String model;
    protected String vehicleRegistrationNumber;
    public abstract double calculateRentalPricePerKilometer();

    public Vehicle(Brand brand, String model, String vehicleRegistrationNumber) {
        super();
        try {
            setId();
            setBrand(brand);
            setModel(model);
            setVehicleRegistrationNumber(vehicleRegistrationNumber);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public UUID getId() {
        return id;
    };

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
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
