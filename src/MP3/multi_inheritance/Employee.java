package MP3.multi_inheritance;

import MP3.ObjectPlus;
import java.util.UUID;

public class Employee extends ObjectPlus {

    private UUID id;
    private String firstName;
    private String lastName;


    public Employee(String firstName, String lastName) {
        super();

        setId();
        setFirstName(firstName);
        setLastName(lastName);

        // dodaj do ekstensji
        addToExtent();
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
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
}
