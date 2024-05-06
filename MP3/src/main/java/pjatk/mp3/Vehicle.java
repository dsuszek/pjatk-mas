package pjatk.mp3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pjatk.mp3.Utils.checkCorrectnessOfStringAttribute;

public abstract class Vehicle extends ObjectPlus {
    // Wszystkie atrybuty klasy Vehicle muszą być widoczne dla klas dziedziczących po niej - używamy ograniczenia widoczności protected.
    protected UUID id;
    protected Brand brand;
    protected String model;
    protected CompanyBranch companyBranch;
    protected Set<String> damages = new HashSet<>(); // atrybut powtarzalny
    protected Set<Rental> rentals = new HashSet<>();
    protected Set<VehicleInsurance> vehicleInsurances = new HashSet<>();
    protected String vehicleRegistrationNumber;
    public abstract double calculateRentalPricePerKilometer();

    public Vehicle(Brand brand, String model, String vehicleRegistrationNumber, CompanyBranch companyBranch) {
        super();
        try {
            setId();
            setBrand(brand);
            setModel(model);
            setVehicleRegistrationNumber(vehicleRegistrationNumber);
            setCompanyBranch(companyBranch);
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
        if (this.brand == null && brand != null) {
            // nowa marka - nie przypisano wcześniej żadnej marki
            this.brand = brand;

            if (brand.getVehicles().contains(this)) {
                return;
            }
            brand.addVehicle(this);
        } else if (this.brand != null && brand == null) {
            // usunięcie marki
            Brand tmp = this.brand;
            this.brand = null;
            tmp.removeVehicle(this);
        } else if (this.brand != null && brand != null) {
            // zmiana marki
            Brand tmp = this.brand;
            this.brand = null;
            tmp.removeVehicle(this);

            this.brand = brand;
            brand.addVehicle(this);
        }
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        checkCorrectnessOfStringAttribute(model, "Model");
        this.model = model;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        checkCorrectnessOfStringAttribute(vehicleRegistrationNumber, "Vehicle registration number");
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
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

            if (companyBranch.getVehicles().contains(this)) {
                return;
            }
            companyBranch.addVehicleQualified(this);
        } else if (this.companyBranch != null && companyBranch == null) {
            // usunięcie oddziału firmy
            CompanyBranch tmp = this.companyBranch;
            this.companyBranch = null;
            tmp.removeVehicleQualified(this);
        } else if (this.companyBranch != null && companyBranch != null) {
            // zmiana oddziału firmy
            CompanyBranch tmp = this.companyBranch;
            this.companyBranch = null;
            tmp.removeVehicleQualified(this);

            this.companyBranch = companyBranch;
            companyBranch.addVehicleQualified(this);
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
        if (rental.getVehicle() == null) {
            rental.setVehicle(this);
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
        rental.setVehicle(null);
    }

    public Set<VehicleInsurance> getVehicleInsurances() {
        return Collections.unmodifiableSet(vehicleInsurances);
    }

    public void addVehicleInsurance(VehicleInsurance vehicleInsurance) {
        if (vehicleInsurance == null) {
            throw new IllegalArgumentException("Empty vehicle insurance cannot be added to the history.");
        }

        if (this.vehicleInsurances.contains(vehicleInsurance)) {
            return;
        }

        this.vehicleInsurances.add(vehicleInsurance);
        if (vehicleInsurance.getVehicle() == null) {
            vehicleInsurance.setVehicle(this);
        }
    }

    public void removeVehicleInsurance(VehicleInsurance vehicleInsurance) {
        if (vehicleInsurance == null) {
            throw new IllegalArgumentException("Empty vehicle insurance cannot be removed from the history.");
        }

        if (!this.vehicleInsurances.contains(vehicleInsurance)) {
            return;
        }
        this.vehicleInsurances.remove(vehicleInsurance);
        vehicleInsurance.setVehicle(null);
    }
}
