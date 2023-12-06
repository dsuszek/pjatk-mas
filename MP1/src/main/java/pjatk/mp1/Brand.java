package pjatk.mp1;

import java.io.Serializable;
import java.time.LocalDate;

public class Brand implements Serializable {

    private long id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private Integer foundationYear; // atrybut opcjonalny

    public Brand(long id, String name) {
        setId(id);
        setName(name);
    }

    public Brand(long id, String name, String originCountry) {
        setId(id);
        setName(name);
        setOriginCountry(originCountry);
    }

    public Brand(long id, String name, Integer foundationYear) {
        setId(id);
        setName(name);
        setFoundationYear(foundationYear);
    }

    public Brand(long id, String name, String originCountry, Integer foundationYear) {
        setId(id);
        setName(name);
        setOriginCountry(originCountry);
        setFoundationYear(foundationYear);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {

        if (foundationYear < 1800) {
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
