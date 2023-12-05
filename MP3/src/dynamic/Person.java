package dynamic;

import MP3.ObjectPlus;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Person extends ObjectPlus {

    // wszystkie atrybuty klasy Person muszą być widoczne dla klas dziedziczących po niej - używamy ograniczenia protected
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        super();

        // set new fields
        setId();
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);

        // add to extent
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() { // atrybut pochodny
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Person ID: " + getId() + "\n" +
                "- first name: " + getFirstName() + "\n" +
                "- last name: " + getLastName() + "\n" +
                "- birth date: " + getBirthDate() + "\n" +
                "- age: " + getAge();
    }
}
