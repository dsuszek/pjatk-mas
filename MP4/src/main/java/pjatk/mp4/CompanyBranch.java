package pjatk.mp4;

import java.util.*;

import static pjatk.mp4.Utils.checkCorrectnessOfStringAttribute;

public class CompanyBranch extends ObjectPlus4 {
    private UUID id;
    private String name;
    private Address address;
    private Region region;
    private Map<UUID, Vehicle> vehiclesQualified = new HashMap<>();

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

    public void addVehicleQualified(Vehicle vehicle) {
        // sprawdzenie, czy ten pojazd został już przypisany do któregoś z oddziałów firmy
        if (!vehiclesQualified.containsKey(vehicle.getId())) { // jeśli ten pojazd nie może zostać znaleziony na podstawie identyfikatora
            vehiclesQualified.put(vehicle.getId(), vehicle);

            // połączenie zwrotne
            if (vehicle.getCompanyBranch() == null) {
                vehicle.setCompanyBranch(this);
            }
        }
    }

    public Vehicle findVehicleQualified(UUID id) {
        // sprawdzenie, czy dane o tym pojeździe są już dostępne
        if (!vehiclesQualified.containsKey(id)) {
            throw new IllegalArgumentException("Unable to find car with ID: " + id);
        }

        return vehiclesQualified.get(id);
    }

    public void removeVehicleQualified(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        if (!vehiclesQualified.containsKey(vehicle.getId())) {
            return;
        }

        vehiclesQualified.remove(vehicle.getId());
        vehicle.setCompanyBranch(null);
    }

    public Set<Vehicle> getVehicles() {
        return Set.copyOf(this.vehiclesQualified.values());
    }


    @Override
    public String toString() {
        return "Company branch: " + name +
                "\nRegion: " + region.getName() +
                "\nAddress: " + address + "\n";
    }
}
