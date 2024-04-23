package pjatk.mp2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pjatk.mp2.Utils.checkCorrectnessOfStringAttribute;

public class Client {

    private UUID id;
    private String firstName;
    private String lastName;

    private Set<Rental> rentals = new HashSet<>(); // Rental to klasa asocjacyjna — liczności 1...* - jeden klient może wiele razy wynajmować auto

    public Client(String firstName, String lastName) {
        setId();
        setFirstName(firstName);
        setLastName(lastName);
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

    @Override // Przesłonięcie metody.
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
