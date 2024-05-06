package pjatk.mp3;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static pjatk.mp3.Utils.*;

public class Customer extends Person {
    private LocalDate currentDrivingLicenceIssueDate;
    private LocalDate currentDrivingLicenceExpirationDate;

    private Set<Rental> rentals = new HashSet<>(); // Rental to klasa asocjacyjna — liczności 1...* - jeden klient może wiele razy wynajmować auto

    public Customer(String firstName, String lastName, LocalDate birthDate, LocalDate currentDrivingLicenceIssueDate, LocalDate currentDrivingLicenceExpirationDate) {
        super(firstName, lastName, birthDate);
        try {
            setCurrentDrivingLicenceIssueDate(currentDrivingLicenceIssueDate);
            setCurrentDrivingLicenceExpirationDate(currentDrivingLicenceExpirationDate);
        } catch (Exception e) {
            e.printStackTrace();
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

    public LocalDate getCurrentDrivingLicenceIssueDate() {
        return currentDrivingLicenceIssueDate;
    }

    public void setCurrentDrivingLicenceIssueDate(LocalDate currentDrivingLicenceIssueDate) {
        checkIfDateIsNotInFuture(currentDrivingLicenceIssueDate, "Driving licence issue date");
        this.currentDrivingLicenceIssueDate = currentDrivingLicenceIssueDate;
    }

    public LocalDate getCurrentDrivingLicenceExpirationDate() {
        return currentDrivingLicenceExpirationDate;
    }

    public void setCurrentDrivingLicenceExpirationDate(LocalDate currentDrivingLicenceExpirationDate) {
        checkIfDateIsNotInPast(currentDrivingLicenceExpirationDate, "Driving licence expiration date");
        this.currentDrivingLicenceExpirationDate = currentDrivingLicenceExpirationDate;
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Customer ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName;
    }
}
