package pjatk.mp3;

import java.time.LocalDate;
import java.util.Set;

import static pjatk.mp3.Utils.checkCorrectnessOfSetOfStringsAttribute;
import static pjatk.mp3.Utils.checkIfDateIsNotInPast;

public class OfficeWorker extends Employee {
    protected Set<String> officeWorkSkills;
    protected LocalDate dateUntilWhenSightTestIsValid;
    public OfficeWorker(String firstName, String lastName, LocalDate birthDate, Set<String> officeWorkSkills, LocalDate dateUntilWhenSightTestIsValid) {
        super(firstName, lastName, birthDate);
        setOfficeWorkSkills(officeWorkSkills);
        setDateUntilWhenSightTestIsValid(dateUntilWhenSightTestIsValid);
    }

    public Set<String> getOfficeWorkSkills() {
        return officeWorkSkills;
    }

    public void setOfficeWorkSkills(Set<String> officeWorkSkills) {
        checkCorrectnessOfSetOfStringsAttribute(officeWorkSkills, "officeWorkSkills");
        this.officeWorkSkills = officeWorkSkills;
    }

    public LocalDate getDateUntilWhenSightTestIsValid() {
        return dateUntilWhenSightTestIsValid;
    }

    public void setDateUntilWhenSightTestIsValid(LocalDate dateUntilWhenSightTestIsValid) {
        checkIfDateIsNotInPast(dateUntilWhenSightTestIsValid, "Date until when sight test is valid");
        this.dateUntilWhenSightTestIsValid = dateUntilWhenSightTestIsValid;
    }

    public boolean isSightTestValid() {
        return (!this.dateUntilWhenSightTestIsValid.isBefore(LocalDate.now()));
    }

    @Override
    public String toString() {
        return "Office worker ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nAge: " + getAge() +
                "\nSkills relevant for office work: " + getOfficeWorkSkills().toString() +
                "\nHas valid sight test: " + isSightTestValid();
    }
}
