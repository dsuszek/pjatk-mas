package pjatk.mp1;

public class Client {

    private long id;
    private String firstName;
    private String lastName;

    public Client(long id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("ID must be positive number.");
        }
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override // przesłonięcie metody
    public String toString() {
        return "Client ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName;
    }

}
