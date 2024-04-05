package pjatk.mp2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static pjatk.mp2.Utils.checkCorrectnessOfId;
import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Brand {

    private int id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private int foundationYear; // atrybut opcjonalny
    private List<Car> cars = new ArrayList<>();

    public Brand(int id, String name) {
        setId(id);
        setName(name);
    }

    public Brand(int id, String name, String originCountry) {
        setId(id);
        setName(name);
        setOriginCountry(originCountry);
    }

    public Brand(int id, String name, Integer foundationYear) {
        setId(id);
        setName(name);
        setFoundationYear(foundationYear);
    }

    public Brand(int id, String name, String originCountry, Integer foundationYear) {
        setId(id);
        setName(name);
        setOriginCountry(originCountry);
        setFoundationYear(foundationYear);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        checkCorrectnessOfId(id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkCorrectnessOfStringAttribute(name);
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        checkCorrectnessOfStringAttribute(originCountry);
        this.originCountry = originCountry;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {

        if (foundationYear <= 1800) {
            throw new IllegalArgumentException("Foundation year must be greater than 1800.");
        }

        if (foundationYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Foundation year cannot be in the future.");
        }

        this.foundationYear = foundationYear;
    }

    @Override
    public String toString() {
        String info = "";

        for (Car car : cars) {
            info += " * " + car.getBrand().getName() + " " + car.getModel() + "\n";
        }

        if (!(info == null))
            info = info.indent(2);

        return  "All cars from brand " + getName() + " available in our company are the following: \n" + info;
    }
}
