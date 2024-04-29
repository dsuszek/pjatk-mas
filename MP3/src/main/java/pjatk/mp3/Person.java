package pjatk.mp3;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import static pjatk.mp3.Utils.checkCorrectnessOfStringAttribute;

public abstract class Person extends ObjectPlus {

    // Wszystkie atrybuty klasy Person muszą być widoczne dla klas dziedziczących po niej - używamy ograniczenia protected.
    protected UUID id;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;

    public Person(String firstName, String lastName, LocalDate birthDate) {
       super();
       try {
           setId();
           setFirstName(firstName);
           setLastName(lastName);
           setBirthDate(birthDate);
       } catch (Exception e) {
           e.printStackTrace();
           removeFromExtent();
       }
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
        checkCorrectnessOfStringAttribute("First name", firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        checkCorrectnessOfStringAttribute("Last name", lastName);
        this.lastName = lastName;
    }







    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }

        this.birthDate = birthDate;
    }

    public int getAge() { // atrybut pochodny
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Person ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nBirth date: " + birthDate +
                "\nAge: " + getAge();
    }
}
