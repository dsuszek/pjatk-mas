package pjatk.mp1;

import java.io.Serializable;
import java.time.LocalDate;

import static pjatk.mp1.Utils.checkCorrectnessOfId;
import static pjatk.mp1.Utils.checkCorrectnessOfStringAttribute;

public class Brand implements Serializable {

    private int id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private Integer foundationYear; // atrybut opcjonalny

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

    public long getId() {
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

    @Override // przesłonięcie metody
    public String toString() {
        return "Brand ID: " + id +
                "\nName: " + getName() +
                "\nOrigin country: " + getOriginCountry() +
                "\nFoundation year: " + getFoundationYear();
    }
}
