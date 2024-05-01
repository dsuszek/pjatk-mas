package pjatk.mp3;

import java.util.*;

import static pjatk.mp3.Utils.*;

public class Car extends Vehicle {
    private double engineSize;
    private EnumSet<CarTypes> carTypes = EnumSet.of(CarTypes.CAR);

    public Car(Brand brand, String model, String vehicleRegistrationNumber, double engineSize) {
        super(brand, model, vehicleRegistrationNumber);
        try {
            setId();
            setBrand(brand);
            setModel(model);
            setVehicleRegistrationNumber(vehicleRegistrationNumber);
            setEngineSize(engineSize);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Car(Brand brand, String model, String vehicleRegistrationNumber, double engineSize, EnumSet<CarTypes> carTypes) {
        super(brand, model, vehicleRegistrationNumber);
        try {
            setId();
            setBrand(brand);
            setModel(model);
            setVehicleRegistrationNumber(vehicleRegistrationNumber);
            setEngineSize(engineSize);
            setCarTypes();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        if (engineSize == null) {
            return;
        }

        checkCorrectnessOfNumericalValueGreaterThanZero(engineSize, "Engine size");
        this.engineSize = engineSize;
    }

    public EnumSet<CarTypes> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes() {
        this.carTypes.add(CarTypes.FAMILY_CAR);
    }

    // @TODO dokonczyc metodę
    // im większa pojemność silnika, tym wyższa opłata za każdy przejechany kilometr
    @Override
    public double calculateRentalPricePerKilometer() {
        // dla samochodów elektrycznych opłata za każdy przejechany kilometr jest stała i wynosi 1.0
        if (carTypes.contains(CarTypes.ELECTRIC_CAR)) {
            return 1.0d;
        } else {
            return Math.round(0.75 * engineSize * 100.0)/100.0;
        }
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        if (this.companyBranch == null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nEngine size: " + engineSize +
                    "\nCar types: " + carTypes +
                    "\nCompany branch: "  +
                    "\nDamages: " + damages +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        }

        return "Car " + id +
                "\nBrand: " + brand.getName() +
                "\nModel: " + model +
                "\nEngine size: " + engineSize +
                "\nCar types: " + carTypes +
                "\nCompany branch: " + companyBranch +
                "\nDamages: " + damages +
                "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
    }
}
