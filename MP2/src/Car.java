
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Car extends ObjectPlus {

    private UUID id;
    private Brand brand;  // atrybut złożony
    private String model;
    private String type;
    private Double engineSize; // atrybut opcjonalny
    private Set<String> damages = new HashSet<>(); // atrybut powtarzalny
    private Set<Rental> rentals = new HashSet<>(); // Rental to klasa asocjacyjna - liczności 1..* - jeden samochód może być wynajmowany wiele razy
    private Branch branch; // asocjacja

    public Car(String model, String type) {
        setId();
        setModel(model);
        setType(type);
    }

    // przeciążenie konstruktora - ten zawiera również engineSize, który jest atrybutem opcjonalnym
    public Car(String model, String type, Double engineSize) {
        setId();
        setModel(model);
        setType(type);
        setEngineSize(engineSize);
    }

    @Override // przesłonięcie metody
    public String toString(){
        if (this.engineSize != null) {
            return "Car ID: " + id +
                    "\nBrand: " + brand.getName() +
                    "\nModel: " + model +
                    "\nType: " + type +
                    "\nEngine size: " + engineSize + "\n";
        }

        return "Car " + id +
                "\nBrand: " + brand.getName() +
                "\nModel: " + model +
                "\nType: " + type + "\n";
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
        Utils.checkCorrectnessOfStringAttribute(damage);

        this.damages.add(damage);
    }

    public void removeDamage(String damage) {
        this.damages.remove(damage);
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch; // @TODO warunki!
    }

    public Set<Rental> getRentals() {
        return Collections.unmodifiableSet(rentals);
    }
    public void addRental(Rental rental) throws Exception {
        if(rental == null) {
            throw new IllegalArgumentException();
        }

        if (this.rentals.contains(rental)) {
            return;
        }

        this.rentals.add(rental);
        if(rental.getCar() == null) {
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
        if (branch == null) {
            throw new IllegalArgumentException("Empty branch cannot be deleted.");
        }

        this.setBranch(null);
    }
}
