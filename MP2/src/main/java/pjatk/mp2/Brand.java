package pjatk.mp2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Brand implements Serializable {

    private UUID id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private Integer foundationYear; // atrybut opcjonalny
    private List<Car> cars = new ArrayList<>();

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

    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }

        if (this.cars.contains(car)) {
            return;
        }

        this.cars.add(car);
        if (car.getBrand() == null) {
            car.setBrand(this);
        }
    }

    public void removeCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }

        if (!cars.contains(car)) {
            return;
        }

        cars.remove(car);
        car.setBrand(null);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("");

        for (Car car : cars) {
            info.append(" * ").append(car.getBrand().getName()).append(" ").append(car.getModel()).append("\n");
        }

        return  "All cars from brand " + getName() + " available in our company are the following: \n" + info;
    }
}
