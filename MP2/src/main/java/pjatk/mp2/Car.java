package pjatk.mp2;

import java.util.*;

import static pjatk.mp2.Utils.*;

public class Car extends ObjectPlus {

    private UUID id;
    private Brand brand;  // atrybut złożony
    private String model;
    private String type;
    private Double engineSize; // atrybut opcjonalny
    private CompanyBranch companyBranch;
    private Set<String> damages = new HashSet<>(); // atrybut powtarzalny
    private Set<Rental> rentals = new HashSet<>();

    public Car(Brand brand, String model, String type) {
        super();
        try {
            setId();
            setBrand(brand);
            setModel(model);
            setType(type);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    // przeciążenie konstruktora - ten zawiera również engineSize, który jest atrybutem opcjonalnym
    public Car(Brand brand, String model, String type, Double engineSize) {
        super();
        try {
            setId();
            setBrand(brand);
            setModel(model);
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

    public void addRental(Rental rental) throws Exception {
        if (rental == null) {
            throw new IllegalArgumentException();
        }

        if (this.rentals.contains(rental)) {
            return;
        }

        this.rentals.add(rental);
        if (rental.getCar() == null) {
            rental.setCar(this);
        }
    }

    public void removeRental(Rental rental) throws Exception {
        if (rental == null) {
            throw new IllegalArgumentException("Empty rental cannot be removed from the history.");
        }

        if (!this.rentals.contains(rental)) {
            return;
        }
        this.rentals.remove(rental);
        rental.setCar(null);
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        if (this.engineSize != null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nType: " + type +
                    "\nEngine size: " + engineSize;
        }

        return "Car " + id +
                "\nBrand: " + brand.getName() +
                "\nModel: " + model +
                "\nType: " + type;
    }
}
