package MP1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Car {

    private long id;
    private Brand brand;  // atrybut złożony
    private String model;
    private String type;
    private Double engineSize; // atrybut opcjonalny
    private Set<String> damages = new HashSet<>(); // atrybut powtarzalny

    public Car(long id, Brand brand, String model, String type) {
        setId(id);
        setBrand(brand);
        setModel(model);
        setType(type);
    }

    // przeciążenie konstruktora - ten zawiera również engineSize, który jest atrybutem opcjonalnym
    public Car(long id, Brand brand, String model, String type, Double engineSize) {
        setId(id);
        setBrand(brand);
        setModel(model);
        setType(type);
        setEngineSize(engineSize);
    }

    @Override // przesłonięcie metody
    public String toString(){
        if (this.engineSize != null) {
            return "MP1.Car ID: " + id +
                    "\nMP1.Brand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nType: " + type +
                    "\nEngine size: " + engineSize;
        }

        return "MP1.Car " + id +
                "\nMP1.Brand: " + brand.getName() +
                "\nModel: " + model +
                "\nType: " + type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {

        if (id < 1) {
            throw new IllegalArgumentException("ID must be positive number.");
        }
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
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
        if(engineSize == null) {
            return;
        }

        if (engineSize <= 0) {
            throw new IllegalArgumentException("Engine size must be greater than 0.");
        }

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

    public void checkCorrectnessOfStringAttribute(String attribute) {
        if (attribute == null || attribute.isBlank()) {
            throw new IllegalArgumentException(attribute + " cannot be neither empty string nor null.");
        }
    }

}
