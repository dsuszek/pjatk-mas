package pjatk.mp2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static pjatk.mp2.Utils.checkCorrectnessOfId;
import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Unit extends ObjectPlusPlus {
    private int id;
    private String name;
    private Address address;
    private Region region;
    private Map<Integer, Car> carsQualified = new HashMap<>();

    public Unit(int id, String name) {
        super();
        try {
            setId(id);
            setName(name);
        } catch (Exception e) {
            removeFromExtent();
        }
    }

    public void addCarQualified(Car car) {
        // check if we have already included this car in the branch
        if (!carsQualified.containsKey(car.getId())) { // if this car is unknown
            carsQualified.put(car.getId(), car);

            // add the reverse connection
            car.setBranch(this);
        }
    }

    public Car findCarQualified(int id) throws Exception {
        // check if we have the info
        if (!carsQualified.containsKey(id)) {
            throw new Exception("Unable to find car with ID: " + id);
        }

        return carsQualified.get(id);
    }

    public void removeCars() {
        carsQualified.clear();
    }


    @Override
    public String toString() {
        String info = "";

        if (carsQualified.isEmpty()) {
            info = "Branch " + name + " with ID: " + id + " doesn't have any cars assigned.\n";

        } else {
            info = "Branch " + name + " with ID: " + id + " has the following cars: \n";
            Iterator<Map.Entry<Integer, Car>> iterator = carsQualified.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Car> entry = iterator.next();
                info += " * " + entry.getValue().getBrand().getName() + " " + entry.getValue().getModel() + " with ID: " + entry.getKey() + "\n";
            }
        }
        return info;
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
}
