package pjatk.mp3;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static pjatk.mp3.Utils.checkCorrectnessOfStringAttribute;

public class Client extends Person {
    private LocalDate drivingLicenceIssueDate;
    private LocalDate drivingLicenceExpirationDate;

    private Set<Rental> rentals = new HashSet<>(); // Rental to klasa asocjacyjna — liczności 1...* - jeden klient może wiele razy wynajmować auto

    public Client(String firstName, String lastName, LocalDate birthDate, LocalDate drivingLicenceIssueDate, LocalDate drivingLicenceExpirationDate) {
        super(firstName, lastName, birthDate);
        try {


        } catch (Exception e) {
            removeFromExtent();
        }
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
        if (rental == null) {
            throw new IllegalArgumentException();
        }

        if (this.rentals.contains(rental)) {
            return;
        }

        this.rentals.add(rental);
        if (rental.getClient() == null) {
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
