package pjatk.mp2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Region extends ObjectPlus {
    private UUID id;
    private String name;
    private List<CompanyBranch> companyBranches = new ArrayList<>();
    private static HashSet<CompanyBranch> allCompanyBranches = new HashSet<>();

    public Region(String name) {
        super();
        try {
            setId();
            setName(name);
        } catch (Exception e) {
            removeFromExtent();
        }
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

    public void addCompanyBranch(CompanyBranch companyBranch) {
        if (!companyBranches.contains(companyBranch)) {
            // Sprawdzenie, czy oddział firmy nie został już przypisany do żadnego z pozostałych regionów
            if (allCompanyBranches.contains(companyBranch)) {
                throw new Error("Ten oddział firmy został już przypisany do innego regionu.");
            }
            // Przypisanie oddziału firmy do wybranego oddziału
            companyBranches.add(companyBranch);

            // Dodanie oddziału firmy do zbioru oddziałów, które już mają przypisany oddział
            // Ta część kodu zapobiega współdzieleniu części
            allCompanyBranches.add(companyBranch);
        }
    }

    public void removeCompanyBranch(CompanyBranch companyBranch) throws Exception {
        // Lista wszystkich samochodów
        List<Car> cars = (List<Car>) getExtent(Car.class);

        // Sprawdzenie, czy użytkownik nie próbuje usunąć nieistniejącego oddziału firmy
        if (companyBranch == null) {
            throw new IllegalArgumentException("Company branch cannot be null.");
        }

        if (!this.companyBranches.contains(companyBranch)) {
            return;
        }

        cars.forEach(c -> c.setCompanyBranch(null));
        allCompanyBranches.remove(companyBranch);
        companyBranch.removeFromExtent();
    }

    public void removeRegion() throws Exception {
        List<Car> cars = (List<Car>) getExtent(Car.class);

        for (CompanyBranch companyBranch : this.companyBranches) {
            removeCompanyBranch(companyBranch);
        }

        this.companyBranches.clear();
        this.removeFromExtent();
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("Region with ID: " + id + " includes the following branches: \n");

        for (CompanyBranch companyBranch : companyBranches) {
            info.append(companyBranch.getName()).append("\n");
        }

        return info.toString();
    }
}
