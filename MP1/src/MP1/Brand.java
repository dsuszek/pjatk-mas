package MP1;

import java.time.LocalDate;

public class Brand {

    private long id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private int foundationYear; // atrybut opcjonalny

    public Brand(long id, String name, String originCountry, int foundationYear) {
        setId(id);
        setName(name);
        setOriginCountry(originCountry);
        setFoundationYear(foundationYear);
    }

    public Brand(long id, String name) {
        this.id = id;
        this.name = name;
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

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {

        if (foundationYear < 1800) {
            throw new IllegalArgumentException("Foundation year must be greater than 1800.");
        }

        if (foundationYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Foundation year cannot be after current year.");
        }

        this.foundationYear = foundationYear;
    }
}
