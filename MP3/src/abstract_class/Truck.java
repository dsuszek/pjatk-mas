package abstract_class;

public class Truck extends Vehicle {

    private double maximumAuthorisedMass;

    public Truck(String brand, String model, String vehicleRegistrationNumber, double maximumAuthorisedMass) {
        super(brand, model, vehicleRegistrationNumber);

        setMaximumAuthorisedMass(maximumAuthorisedMass);
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
    public double calculateRentalPricePerDay() {
        // im większa masa dopuszczalna całkowita (w tonach), tym droższy wynajem samochodu
        return 200 + 20 * maximumAuthorisedMass;
    }

    @Override
    public String toString() {
        return "Truck ID: " + getId() + "\n" +
                "- brand: " + getBrand() + "\n" +
                "- model: " + getModel() + "\n" +
                "- registration number: " + getVehicleRegistrationNumber() + "\n" +
                "- maximum authorised mass: " + getMaximumAuthorisedMass() + "\n" +
                "- price of rental per day: " + calculateRentalPricePerDay();
    }
}
