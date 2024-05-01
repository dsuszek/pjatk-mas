package pjatk.mp3;

import java.util.*;

import static pjatk.mp3.Utils.*;

public class Car extends Vehicle {
    private double engineSize;

    // @TODO dodac konstruktor z carType
    private EnumSet<CarType> carTypes = EnumSet.of(CarType.CAR);

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


    // @TODO dokonczyc metodę
    // im większa pojemność silnika, tym wyższa opłata za każdy przejechany kilometr
    @Override
    public double calculateRentalPricePerKilometer() {
        return Math.round(0.75 * engineSize);
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        if (this.companyBranch == null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nEngine size: " + engineSize +
                    "\nCompany branch: "  +
                    "\nDamages: " + damages +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        }

        return "Car " + id +
                "\nBrand: " + brand.getName() +
                "\nModel: " + model +
                "\nEngine size: " + engineSize +
                "\n" + companyBranch +
                "\nDamages: " + damages +
                "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
    }
}
