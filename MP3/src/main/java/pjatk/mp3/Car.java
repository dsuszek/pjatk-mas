package pjatk.mp3;

import java.util.*;

import static pjatk.mp3.Utils.*;

public class Car extends Vehicle {

    private UUID id;
    private String type; // atrybut opcjonalny
    private double engineSize;
    private CompanyBranch companyBranch;
    private Set<String> damages = new HashSet<>(); // atrybut powtarzalny
    private Set<Rental> rentals = new HashSet<>();
    private Set<CarInsurance> carInsurances = new HashSet<>();
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

    // przeciążenie konstruktora - ten zawiera również type, który jest atrybutem opcjonalnym
    public Car(Brand brand, String model, String vehicleRegistrationNumber, String type, double engineSize) {
        super(brand, model, vehicleRegistrationNumber);
        try {
            setId();
            setBrand(brand);
            setModel(model);
            setVehicleRegistrationNumber(vehicleRegistrationNumber);
            setType(type);
            setEngineSize(engineSize);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        if (this.brand == null && brand != null) {
            // nowa marka - nie przypisano wcześniej żadnej marki
            this.brand = brand;

            if (brand.getCars().contains(this)) {
                return;
            }
            brand.addCar(this);
        } else if (this.brand != null && brand == null) {
            // usunięcie marki
            Brand tmp = this.brand;
            this.brand = null;
            tmp.removeCar(this);
        } else if (this.brand != null && brand != null) {
            // zmiana marki
            Brand tmp = this.brand;
            this.brand = null;
            tmp.removeCar(this);

            this.brand = brand;
            brand.addCar(this);
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        checkCorrectnessOfStringAttribute("Model", model);
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        checkCorrectnessOfStringAttribute("Type", type);
        this.type = type;
    }

    public void setEngineSize(Double engineSize) {
        if (engineSize == null) {
            return;
        }

        checkCorrectnessOfNumericalValueGreaterThanZero(engineSize, "Engine size");
        this.engineSize = engineSize;
    }

    public Set<String> getDamages() {
        return Collections.unmodifiableSet(damages);
    }

    public void addDamage(String damage) {
        checkCorrectnessOfStringAttribute("Damage", damage);
        this.damages.add(damage);
    }

    public void removeDamage(String damage) {
        this.damages.remove(damage);
    }

    public CompanyBranch getCompanyBranch() {
        return companyBranch;
    }

    public void setCompanyBranch(CompanyBranch companyBranch) {
        if (this.companyBranch == null && companyBranch != null) {
            // pojazd przypisany do oddziału firmy po raz pierwszy
            this.companyBranch = companyBranch;

            if (companyBranch.getCars().contains(this)) {
                return;
            }
            companyBranch.addCarQualified(this);
        } else if (this.companyBranch != null && companyBranch == null) {
            // usunięcie oddziału firmy
            CompanyBranch tmp = this.companyBranch;
            this.companyBranch = null;
            tmp.removeCarQualified(this);
        } else if (this.companyBranch != null && companyBranch != null) {
            // zmiana oddziału firmy
            CompanyBranch tmp = this.companyBranch;
            this.companyBranch = null;
            tmp.removeCarQualified(this);

            this.companyBranch = companyBranch;
            companyBranch.addCarQualified(this);
        }
    }

    public Set<Rental> getRentals() {
        return Collections.unmodifiableSet(rentals);
    }

    public void addRental(Rental rental) throws IllegalArgumentException {
        if (rental == null) {
            throw new IllegalArgumentException("Empty rental cannot be added to the history.");
        }

        if (this.rentals.contains(rental)) {
            return;
        }

        this.rentals.add(rental);
        if (rental.getCar() == null) {
            rental.setCar(this);
        }
    }

    public void removeRental(Rental rental) throws IllegalArgumentException {
        if (rental == null) {
            throw new IllegalArgumentException("Empty rental cannot be removed from the history.");
        }

        if (!this.rentals.contains(rental)) {
            return;
        }
        this.rentals.remove(rental);
        rental.setCar(null);
    }

    public Set<CarInsurance> getCarInsurances() {
        return Collections.unmodifiableSet(carInsurances);
    }

    public void addCarInsurance(CarInsurance carInsurance) {
        if (carInsurance == null) {
            throw new IllegalArgumentException("Empty car insurance cannot be added to the history.");
        }

        if (this.carInsurances.contains(carInsurance)) {
            return;
        }

        this.carInsurances.add(carInsurance);
        if (carInsurance.getCar() == null) {
            carInsurance.setCar(this);
        }
    }

    public void removeCarInsurance(CarInsurance carInsurance) {
        if (carInsurance == null) {
            throw new IllegalArgumentException("Empty car insurance cannot be removed from the history.");
        }

        if (!this.carInsurances.contains(carInsurance)) {
            return;
        }
        this.carInsurances.remove(carInsurance);
        carInsurance.setCar(null);
    }

    // @TODO dokonczyc metodę
    // im większa pojemność silnika, tym wyższa opłata za każdy przejechany kilometr
    @Override
    public double calculateRentalPricePerKilometer() {
        return Math.round(0.75 * engineSize);
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        if (this.type != null && this.companyBranch == null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nType: " + type +
                    "\nEngine size: " + engineSize +
                    "\nCompany branch: " + companyBranch +
                    "\nDamages: " + damages +
                    "\nPrice of rental per kilometer: " + calculateRentalPricePerKilometer();
        }

        if (this.type != null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nType: " + type +
                    "\nEngine size: " + engineSize +
                    "\n" +companyBranch +
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
