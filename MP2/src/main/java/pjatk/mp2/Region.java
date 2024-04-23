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

    public void deleteRegion() throws Exception {
        //@TODO poprawić metodę odpowiadającą za usuwanie całości
        removeDependencies();
        removeFromExtent();
    }

    public void addBranch(CompanyBranch companyBranch) {
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

    public void removeDependencies() throws Exception {
        List<Car> cars = Car.getExtentForClass(Car.class);

//        for (CompanyBranch companyBranch : companyBranches) {
//            // usun obiekty clasy Car powiązane z tym obiektem klasy Branch, a następnie usuń branch z listy oddziałów
//            companyBranch.removeCars();
//            companyBranch.removeFromExtent();
//
//            for (Car car : cars) {
//                if(car.getBranch().equals(companyBranch))
//                    car.removeBranch();
//            }
//        }

        this.companyBranches.clear();
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
