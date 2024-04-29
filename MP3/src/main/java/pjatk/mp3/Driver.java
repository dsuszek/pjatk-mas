package pjatk.mp3;

import java.time.LocalDate;

public abstract class Driver extends Person {
    public Driver(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName, birthDate);
    }
}
