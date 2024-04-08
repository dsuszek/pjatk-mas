package pjatk.mp2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static pjatk.mp2.Utils.checkCorrectnessOfId;
import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Client extends ObjectPlusPlus {

    private int id;
    private String firstName;
    private String lastName;

    private Set<Rental> rentals = new HashSet<>(); // Rental to klasa asocjacyjna — liczności 1...* - jeden klient może wiele razy wynajmować auto

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


    public Set<Rental> getRentals() {
        return Collections.unmodifiableSet(rentals);
    }
    public void addRental(Rental rental) throws Exception {
        if(rental == null) {
            throw new IllegalArgumentException();
        }

        if (this.rentals.contains(rental)) {
            return;
        }

        this.rentals.add(rental);
        if(rental.getClient() == null) {
            rental.setClient(this);
        }
    }

    public void removeRental(Rental rental) throws Exception {
        if (rental == null) {
            throw new IllegalArgumentException("Empty rental cannot be removed from the history.");
        }

        if (!this.rentals.contains(rental)) {
            return;
        }
        this.rentals.remove(rental);
        rental.setClient(null);
    }
}