package pjatk.mp3;

import java.time.LocalDate;
import java.util.Set;

import static pjatk.mp3.Utils.*;

public class ManagerOfCarServiceStation extends OfficeWorker implements IManualWorker {
    private Set<String> technicalSkills;
    private LocalDate dateUntilWhenWorkHealthAndSafetyTrainingIsValid;

    public ManagerOfCarServiceStation(String firstName, String lastName, LocalDate birthDate, Set<String> softSkills, LocalDate dateUntilWhenSightTestIsValid, Set<String> technicalSkills, LocalDate dateUntilWhenWorkHealthAndSafetyTrainingIsValid) {
        super(firstName, lastName, birthDate, softSkills, dateUntilWhenSightTestIsValid);
        try {
            setTechnicalSkills(technicalSkills);
            setDateUntilWhenWorkHealthAndSafetyTrainingIsValid(dateUntilWhenWorkHealthAndSafetyTrainingIsValid);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public void setTechnicalSkills(Set<String> technicalSkills) {
        checkCorrectnessOfSetOfStringsAttribute(technicalSkills, "Technical skills");
        this.technicalSkills = technicalSkills;
    }

    public Set<String> getTechnicalSkills() {
        return technicalSkills;
    }

    public void addTechnicalSkill(String technicalSkill) {
        checkCorrectnessOfStringAttribute(technicalSkill, "Technical skill");
        technicalSkills.add(technicalSkill);
    }

    @Override
    public LocalDate getDateUntilWhenWorkHealthAndSafetyTrainingIsValid() {
        return dateUntilWhenWorkHealthAndSafetyTrainingIsValid;
    }

    @Override
    public void setDateUntilWhenWorkHealthAndSafetyTrainingIsValid(LocalDate dateUntilWhenWorkHealthAndSafetyTrainingIsValid) {
        checkIfDateIsNotInPast(dateUntilWhenWorkHealthAndSafetyTrainingIsValid, "Date until when work health and safety training is valid ");
        this.dateUntilWhenWorkHealthAndSafetyTrainingIsValid = dateUntilWhenWorkHealthAndSafetyTrainingIsValid;
    }

    @Override
    public boolean isWorkHealthAndSafetyTrainingValid() {
        return false;
    }

    @Override
    public String toString() {
        return "Manager of car service station ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nAge: " + getAge() +
                "\nSkills relevant for office work: " + officeWorkSkills +
                "\nHas valid sight test: " + isSightTestValid() +
                "\nTechnical skills: " + technicalSkills +
                "\nHas valid work health and safety training: " + isWorkHealthAndSafetyTrainingValid();

    }
}
