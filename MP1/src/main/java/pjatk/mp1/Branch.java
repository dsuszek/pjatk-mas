package pjatk.mp1;

import static pjatk.mp1.Utils.checkCorrectnessOfId;
import static pjatk.mp1.Utils.checkCorrectnessOfStringAttribute;

public class Branch {
    private int id;
    private String name;
    private Address address;

    public Branch(int id, String name, Address address) {
        setId(id);
        setName(name);
        setAddress(address);
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

    @Override // przesłonięcie metody
    public String toString() {
        return "Branch ID: " + id +
                "\nName: " + name +
                "\nAddress: " + address.toString();
    }
}
