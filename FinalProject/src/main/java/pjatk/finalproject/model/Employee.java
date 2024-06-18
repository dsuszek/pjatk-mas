package pjatk.finalproject.model;

import java.time.LocalDate;

public abstract class Employee extends Person {
    public Employee(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName, birthDate);
    }

    //@TODO dokonczyc
    public void addEmployment(Employment employment) {
    }

    public void removeEmployment(Employment employment) {
    }
}
