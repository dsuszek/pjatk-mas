package pjatk.mp1;

public class Branch {
    private long id;
    private String name;
    private Address address;

    public Branch(long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Branch ID: " + id +
                "\nName: " + name +
                "\nAddress: " + address.toString();
    }
}
