package pjatk.mp1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static pjatk.mp1.Utils.*;

public class Car extends ObjectPlus {

    private int id;
    private Brand brand;  // atrybut złożony
    private String model;
    private String type;
    private Double engineSize; // atrybut opcjonalny
    private Set<String> damages = new HashSet<>(); // atrybut powtarzalny

    public Car(int id, Brand brand, String model, String type) {
        super();
        try {
            setId(id);
            setBrand(brand);
            setModel(model);
            setType(type);
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
            throw new IllegalArgumentException("Brand cannot be null");
        }
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        checkCorrectnessOfStringAttribute(model);
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        checkCorrectnessOfStringAttribute(type);
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
        checkCorrectnessOfStringAttribute(damage);
        this.damages.add(damage);
    }

    public void removeDamage(String damage) {
        this.damages.remove(damage);
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

        return "Car " + id +
                "\nBrand: " + brand.getName() +
                "\nModel: " + model +
                "\nType: " + type;
    }
}
