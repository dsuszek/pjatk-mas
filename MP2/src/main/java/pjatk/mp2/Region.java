package pjatk.mp2;

import java.util.ArrayList;
import java.util.List;

import static pjatk.mp2.Utils.checkCorrectnessOfId;
import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Region extends ObjectPlus {
    private int id;
    private String name;
    private List<Branch> branches = new ArrayList<>();

    public Region(String name) {
        super();
        try {
            setId(id);
            setName(name);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public void deleteRegion() throws Exception {
        removeDependencies();
        removeFromExtent();
    }

    public void addBranch(Branch branch) {
        if (!branches.contains(branch)) {
            branches.add(branch);
        }
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

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("Region with ID: " + id + " includes the following branches: \n");

        for (Branch branch : branches) {
            info.append(branch.getName()).append("\n");
        }

        return info.toString();
    }
}
