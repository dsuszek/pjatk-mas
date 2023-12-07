package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Region extends ObjectPlus {
    private UUID id;
    private String name;
    private List<Branch> branches = new ArrayList<>();

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
        removeDependencies();
        removeFromExtent();
    }

    public void addBranch(Branch branch) throws Exception {
        if (!branches.contains(branch)) {
            branches.add(branch);
        }
    }

    @Override
    public String toString() {
        String info = "Region with ID: " + id + " includes the following branches: \n";

        for (Branch branch : branches) {
            info += branch.getName() + "\n";
        }

        return info;
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

    public void removeDependencies() throws Exception {
        List<Car> cars = Car.getExtentForClass(Car.class);

        for (Branch branch : branches) {
            // usun obiekty clasy Car powiązane z tym obiektem klasy Branch, a następnie usuń branch z listy oddziałów
            branch.removeCars();
            branch.removeFromExtent();

            for (Car car : cars) {
                if(car.getBranch().equals(branch))
                    car.removeBranch();
            }
        }

        this.branches.clear();
    }
}
