package pjatk.mp1;

import static pjatk.mp1.Utils.checkCorrectnessOfId;
import static pjatk.mp1.Utils.checkCorrectnessOfStringAttribute;

public class Client {

    private int id;
    private String firstName;
    private String lastName;

    public Client(int id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        checkCorrectnessOfId(id);
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        checkCorrectnessOfStringAttribute(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        checkCorrectnessOfStringAttribute(lastName);
        this.lastName = lastName;
    }

    @Override // przesłonięcie metody
    public String toString() {
        return "Client ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName;
    }

}
