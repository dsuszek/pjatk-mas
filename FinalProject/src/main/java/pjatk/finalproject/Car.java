package pjatk.finalproject;

import java.util.*;

import static pjatk.finalproject.Utils.*;

public class Car extends Vehicle {
    private double engineSize;
    private EnumSet<CarTypes> carTypes = EnumSet.of(CarTypes.CAR);
    private Double suspensionHeight; // w milimetrach, tylko dla samochodów typu SPORT_CAR
    private Set<String> luxuryDesignElements; // tylko dla samochodów typu PREMIUM_CAR
    private Double batteryCapacity; // w kWh, tylko dla samochodów typu ELECTRIC_CAR
    public Car(Brand brand, String model, String vehicleRegistrationNumber, CompanyBranch companyBranch, double engineSize) {
        super(brand, model, vehicleRegistrationNumber, companyBranch);
        try {
            setEngineSize(engineSize);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public Car(Brand brand, String model, String vehicleRegistrationNumber, CompanyBranch companyBranch, double engineSize, EnumSet<CarTypes> carTypes) {
        super(brand, model, vehicleRegistrationNumber, companyBranch);
        try {
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

    @Override
    public double calculateRentalPricePerKilometer() {
        // im większa pojemność silnika, tym wyższa opłata za każdy przejechany kilometr
        // dla samochodów elektrycznych opłata za każdy przejechany kilometr jest stała i wynosi 1.0
        if (carTypes.contains(CarTypes.ELECTRIC_CAR)) {
            return 1.0d;
        } else {
            return Math.round(0.75 * engineSize * 100.0)/100.0;
        }
    }

    public Double getSuspensionHeight() {
        if (!carTypes.contains(CarTypes.SPORT_CAR)) {
            throw new RuntimeException("Information about suspension height is not available for this type of car.");
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
            throw new RuntimeException("Luxury elements are not available for this type of car.");
        }

        return Collections.unmodifiableSet(luxuryDesignElements);
    }

    public void setLuxuryDesignElement(Set<String> luxuryDesignElements) {
        if (!(carTypes.contains(CarTypes.PREMIUM_CAR)) && (!luxuryDesignElements.isEmpty())) {
            throw new RuntimeException("Luxury design elements cannot be added to this type of car.");
        }

        checkCorrectnessOfSetOfStringsAttribute(luxuryDesignElements, "Luxury design elements");

        this.luxuryDesignElements = luxuryDesignElements;
    }

    public void addLuxuryDesignElement(String luxuryDesignElement) {
        if (!(carTypes.contains(CarTypes.PREMIUM_CAR))) {
            throw new RuntimeException("Luxury design element cannot be added to this type of car.");
        }
    }

    public Double getBatteryCapacity() {
        if(!carTypes.contains(CarTypes.ELECTRIC_CAR)) {
            throw new RuntimeException("Information about battery capacity is not available for this type of car.");
        }

        return batteryCapacity;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        if (!(carTypes.contains(CarTypes.ELECTRIC_CAR))) {
            throw new IllegalArgumentException("Information about battery capacity cannot be added to this type of car.");
        }

        checkCorrectnessOfNumericalValueGreaterThanZero(batteryCapacity, "Battery capacity");
        this.batteryCapacity = batteryCapacity;
    }

    @Override // Przesłonięcie metody.
    public String toString() {

        if (carTypes.contains(CarTypes.SPORT_CAR) && suspensionHeight != null) {
            return "Car registration number: " + vehicleRegistrationNumber +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nEngine size: " + engineSize +
                    "\nCar types: " + carTypes +
                    "\nSuspension height: " + suspensionHeight +
                    "\nCompany branch: " + companyBranch.getName() +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        } else if (carTypes.contains(CarTypes.ELECTRIC_CAR) && batteryCapacity != null) {
            return "Car registration number: " + vehicleRegistrationNumber +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nEngine size: " + engineSize +
                    "\nCar types: " + carTypes +
                    "\nBattery capacity: " + batteryCapacity +
                    "\nCompany branch: " + companyBranch.getName() +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        } else if (carTypes.contains(CarTypes.PREMIUM_CAR) && (luxuryDesignElements != null)) {
            return "Car registration number: " + vehicleRegistrationNumber +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nEngine size: " + engineSize +
                    "\nCar types: " + carTypes +
                    "\nLuxury design elements: " + luxuryDesignElements +
                    "\nCompany branch: " + companyBranch.getName() +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        } else {
            return "Car registration number: " + vehicleRegistrationNumber +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nEngine size: " + engineSize +
                    "\nCar types: " + carTypes +
                    "\nCompany branch: " + companyBranch.getName() +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        }
    }
}
