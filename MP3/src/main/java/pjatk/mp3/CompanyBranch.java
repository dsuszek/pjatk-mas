package pjatk.mp3;

import java.util.*;

import static pjatk.mp3.Utils.checkCorrectnessOfStringAttribute;

public class CompanyBranch extends ObjectPlus {
    private UUID id;
    private String name;
    private Address address;
    private Region region;
    private Map<UUID, Car> carsQualified = new HashMap<>();

    private CompanyBranch(String name, Address address, Region region) {
        super();
        try {
            setId();
            setName(name);
            setAddress(address);
            setRegion(region);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public static CompanyBranch createCompanyBranch(String name, Address address, Region region) throws Exception {
        if (region == null) {
            throw new Exception("Region does not exist.");
        }

        // Tworzenie nowego oddziału firmy
        CompanyBranch companyBranch = new CompanyBranch(name, address, region);

        // Dodawanie oddziału firmy do regionu
        region.addCompanyBranch(companyBranch);

        return companyBranch;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        this.address = address;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void addCarQualified(Car car) {
        // Sprawdzenie, czy ten samochód został już przypisany do któregoś z oddziałów firmy
        if (!carsQualified.containsKey(car.getId())) { // Jeśli ten samochód nie może zostać znaleziony na podstawie identyfikatora
            carsQualified.put(car.getId(), car);

            // add the reverse connection
            if (car.getCompanyBranch() == null) {
                car.setCompanyBranch(this);
            }
        }
    }

    public Car findCarQualified(UUID id) {
        // Sprawdzenie, czy dane o tym samochodzie są już dostępne
        if (!carsQualified.containsKey(id)) {
            throw new IllegalArgumentException("Unable to find car with ID: " + id);
        }

        return carsQualified.get(id);
    }

    public void removeCarQualified(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }

        if (!carsQualified.containsKey(car.getId())) {
            return;
        }

        carsQualified.remove(car.getId());
        car.setCompanyBranch(null);
    }

    public Set<Car> getCars() {
        return Set.copyOf(this.carsQualified.values());
    }


    @Override
    public String toString() {
        return "Company branch: " + name +
                "\nRegion: " + region.getName() +
                "\nAddress: " + address + "\n";
    }
}
