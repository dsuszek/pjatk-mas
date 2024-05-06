package pjatk.mp4;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static pjatk.mp4.Utils.checkCorrectnessOfStringAttribute;

public class Brand implements Serializable {

    private UUID id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private Integer foundationYear; // atrybut opcjonalny
    private List<Vehicle> vehicles = new ArrayList<>();

    public Brand(String name) {
        setId();
        setName(name);
    }

    public Brand(String name, String originCountry) {
        setId();
        setName(name);
        setOriginCountry(originCountry);
    }

    public Brand(String name, Integer foundationYear) {
        setName(name);
        setFoundationYear(foundationYear);
    }

    public Brand(String name, String originCountry, Integer foundationYear) {
        setId();
        setName(name);
        setOriginCountry(originCountry);
        setFoundationYear(foundationYear);
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkCorrectnessOfStringAttribute("Name", name);
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        checkCorrectnessOfStringAttribute("Origin country", originCountry);
        this.originCountry = originCountry;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {

        if (foundationYear <= 1800) {
            throw new IllegalArgumentException("Foundation year must be greater than 1800.");
        }

        if (foundationYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Foundation year cannot be in the future.");
        }

        this.foundationYear = foundationYear;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        if (this.vehicles.contains(vehicle)) {
            return;
        }

        this.vehicles.add(vehicle);
        if (vehicle.getBrand() == null) {
            vehicle.setBrand(this);
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        if (!vehicles.contains(vehicle)) {
            return;
        }

        vehicles.remove(vehicle);
        vehicle.setBrand(null);
    }

    public List<Vehicle> getVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    @Override
    public String toString() {
        return name;
    }
}
