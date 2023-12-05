
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Brand {

    private UUID id;
    private String name;
    private String originCountry; // atrybut opcjonalny
    private int foundationYear; // atrybut opcjonalny
    private List<Car> cars = new ArrayList<>();

    public Brand(String name, String originCountry, int foundationYear) {
        setId();
        setName(name);
        setOriginCountry(originCountry);
        setFoundationYear(foundationYear);
    }

    public Brand(String name) {
        setId();
        this.name = name;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
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
