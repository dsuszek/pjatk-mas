package pjatk.mp3;

import java.time.LocalDate;

public abstract class Employee extends Person {
    public Employee(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName, birthDate);
    }
}
