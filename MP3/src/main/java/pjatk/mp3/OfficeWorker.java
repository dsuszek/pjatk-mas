package pjatk.mp3;

import java.time.LocalDate;
import java.util.Set;

import static pjatk.mp3.Utils.checkCorrectnessOfSetOfStringsAttribute;
import static pjatk.mp3.Utils.checkIfDateIsNotInPast;

public class OfficeWorker extends Employee {
    private Set<String> softSkills;
    private LocalDate dateUntilWhenSightTestIsValid;
    public OfficeWorker(String firstName, String lastName, LocalDate birthDate, Set<String> softSkills, LocalDate dateUntilWhenSightTestIsValid) {
        super(firstName, lastName, birthDate);
        setSoftSkills(softSkills);
        setDateUntilWhenSightTestIsValid(dateUntilWhenSightTestIsValid);
    }

    public Set<String> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(Set<String> softSkills) {
        checkCorrectnessOfSetOfStringsAttribute(softSkills, "Soft skills");
        this.softSkills = softSkills;
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
                "\nSoft skills: " + getSoftSkills().toString() +
                "\nHas valid sight test: " + isSightTestValid();
    }
}
