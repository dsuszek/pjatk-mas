package pjatk.mp3;

public class Truck extends Vehicle {
    private double maximumAuthorisedMass;

    public Truck(Brand brand, String model, String vehicleRegistrationNumber, CompanyBranch companyBranch, double maximumAuthorisedMass) {
        super(brand, model, vehicleRegistrationNumber, companyBranch);

        try {
            setMaximumAuthorisedMass(maximumAuthorisedMass);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }
    public double getMaximumAuthorisedMass() {
        return maximumAuthorisedMass;
    }

    public void setMaximumAuthorisedMass(double maximumAuthorisedMass) {
        if (maximumAuthorisedMass < 0) {
            throw new IllegalArgumentException("Maximum authorized mass must be greater than 0.");
        }
        this.maximumAuthorisedMass = maximumAuthorisedMass;
    }

    @Override
    public double calculateRentalPricePerKilometer() {
        // im większa masa dopuszczalna całkowita (w tonach), tym droższy wynajem samochodu
        return Math.round(0.2 * maximumAuthorisedMass * 100.0)/100.0;
    }

    @Override
    public String toString() {
        return "Truck ID: " + getId() +
                "\nBrand: " + getBrand() +
                "\nModel: " + getModel() +
                "\nRegistration number: " + getVehicleRegistrationNumber() +
                "\nMaximum authorised mass: " + getMaximumAuthorisedMass() +
                "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
    }
}
