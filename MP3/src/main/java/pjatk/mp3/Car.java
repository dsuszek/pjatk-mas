package pjatk.mp3;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;

import static pjatk.mp3.Utils.*;

public class Car extends Vehicle {
    private double engineSize;
    private EnumSet<CarTypes> carTypes = EnumSet.of(CarTypes.CAR);
    private Double suspensionHeight; // tylko dla typu samochodów SPORT_CAR
    private Set<String> luxuryDesignElements; // tylko dla typu samochodów PREMIUM_CAR
    private Double batteryCapacity; // tylko dla klasy samochodów ELECTRIC_CAR
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
            setCarTypes(carTypes);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(engineSize, "Engine size");
        this.engineSize = engineSize;
    }

    public EnumSet<CarTypes> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(EnumSet<CarTypes> carTypes) {
        this.carTypes = carTypes;
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

    public Double getSuspensionHeight() {
        if (!carTypes.contains(CarTypes.SPORT_CAR)) {
            throw new RuntimeException("Suspension height cannot be checked for this type of car.");
        }
        return suspensionHeight;
    }

    public void setSuspensionHeight(Double suspensionHeight) {
        if (!(carTypes.contains(CarTypes.SPORT_CAR)) && !(suspensionHeight == null)) {
            throw new IllegalArgumentException("Suspension height cannot be changed for this type of car.");
        } else if (suspensionHeight <= 5.0) {
            throw new IllegalArgumentException("Suspension height must be at least 5 cm.");
        }
        this.suspensionHeight = suspensionHeight;
    }

    public Set<String> getLuxuryDesignElements() {
        if(!carTypes.contains(CarTypes.PREMIUM_CAR)) {
            throw new RuntimeException("This is not a premium car.");
        }

        return Collections.unmodifiableSet(luxuryDesignElements);
    }

    public void setLuxuryDesignElement(Set<String> luxuryDesignElements) {
        if (!(carTypes.contains(CarTypes.PREMIUM_CAR)) && (!luxuryDesignElements.isEmpty())) {
            throw new IllegalArgumentException("Luxury design elements cannot be added to this type of car.");
        }

        this.luxuryDesignElements = luxuryDesignElements;
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
