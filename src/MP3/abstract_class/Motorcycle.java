package MP3.abstract_class;

public class Motorcycle extends Vehicle {

    private double acceleration;

    public Motorcycle(String brand, String model, String vehicleRegistrationNumber, double acceleration) {
        super(brand, model, vehicleRegistrationNumber);

        setAcceleration(acceleration);
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        if (acceleration <= 0) {
            throw new IllegalArgumentException("Acceleration (seconds until 100km/h) must be greater than 0.");
        }
        this.acceleration = acceleration;
    }

    @Override
    public double calculateRentalPricePerDay() {
        // im lepsze przyspieszenie (mniej sekund do 100km/h, tym droższy wynajem motocykla)
        return 100 + (200/acceleration);
    }

    @Override
    public String toString() {
        return "Motorcycle ID: " + getId() + "\n" +
                "- brand: " + getBrand() + "\n" +
                "- model: " + getModel() + "\n" +
                "- registration number: " + getVehicleRegistrationNumber() + "\n" +
                "- acceleration: " + getAcceleration() + "\n" +
                "- price of rental per day: " + calculateRentalPricePerDay();
    }
}
