package pjatk.mp2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static pjatk.mp2.Utils.checkCorrectnessOfId;
import static pjatk.mp2.Utils.checkCorrectnessOfNumericalValueGreaterThanZero;

public class Car extends ObjectPlusPlus {

    private int id;
    private String model;
    private String type;
    private Brand brand;
    private Double engineSize; // atrybut opcjonalny
    private Set<String> damages = new HashSet<>(); // atrybut powtarzalny
    private Set<Rental> rentals = new HashSet<>(); // Rental to klasa asocjacyjna - liczności 1..* - jeden samochód może być wynajmowany wiele razy
    private Unit unit; // asocjacja

    public Car(int id, Brand brand, String model, String type) {
        super();
        try {
            setId(id);
            setBrand(brand);
            setModel(model);
            setType(type);
            addToExtent();
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    // przeciążenie konstruktora - ten zawiera również engineSize, który jest atrybutem opcjonalnym
    public Car(int id, Brand brand, String model, String type, Double engineSize) {
        super();
        try {
            setId(id);
            setBrand(brand);
            setModel(model);
            setType(type);
            setEngineSize(engineSize);
            addToExtent();
        } catch (Exception e) {
            removeFromExtent();
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        checkCorrectnessOfId(id);
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        if (brand == null) {
            throw new IllegalArgumentException("Brand cannot be null.");
        }
        this.brand = brand;
        brand.addCar(this);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        Utils.checkCorrectnessOfStringAttribute(model);
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        Utils.checkCorrectnessOfStringAttribute(type);
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
        Utils.checkCorrectnessOfStringAttribute(damage);
        this.damages.add(damage);
    }

    public void removeDamage(String damage) {
        this.damages.remove(damage);
    }

    public Unit getBranch() {
        return unit;
    }

    public void setBranch(Unit unit) {
        this.unit = unit; // @TODO warunki!
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

    public void removeBranch() throws Exception {
        if (unit == null) {
            throw new IllegalArgumentException("Empty branch cannot be deleted.");
        }

        this.setBranch(null);
    }

    @Override // przesłonięcie metody
    public String toString() {
        if (this.engineSize != null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nType: " + type +
                    "\nEngine size: " + engineSize;
        }

        return "Car ID: " + id +
                "\nBrand: " + brand.getName() +
                "\nModel: " + model +
                "\nType: " + type;
    }
}
