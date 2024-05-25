package pjatk.finalproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static pjatk.finalproject.Utils.*;

public class Mechanic extends Employee implements IManualWorker {
    private Set<String> technicalSkills;
    private LocalDate dateUntilWhenWorkHealthAndSafetyTrainingIsValid;
    protected List<VehicleRepair> vehicleRepairs = new ArrayList<>();

    public Mechanic(String firstName, String lastName, LocalDate birthDate, Set<String> technicalSkills, LocalDate dateUntilWhenWorkHealthAndSafetyTrainingIsValid) {
        super(firstName, lastName, birthDate);
        try {
            setTechnicalSkills(technicalSkills);
            setDateUntilWhenWorkHealthAndSafetyTrainingIsValid(dateUntilWhenWorkHealthAndSafetyTrainingIsValid);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    @Override
    public void setTechnicalSkills(Set<String> technicalSkills) {
        checkCorrectnessOfSetOfStringsAttribute(technicalSkills, "Technical skills");
        this.technicalSkills = technicalSkills;
    }

    @Override
    public Set<String> getTechnicalSkills() {
        return technicalSkills;
    }

    @Override
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
        return (!this.dateUntilWhenWorkHealthAndSafetyTrainingIsValid.isBefore(LocalDate.now()));
    }

    public List<VehicleRepair> getVehicleRepairs() {
        return Collections.unmodifiableList(vehicleRepairs);
    }

    public void addVehicleRepair(VehicleRepair vehicleRepair) {
        if (vehicleRepair == null) {
            throw new IllegalArgumentException("Empty vehicle repair cannot be added to the history.");
        }

        if (this.vehicleRepairs.contains(vehicleRepair)) {
            return;
        }

        this.vehicleRepairs.add(vehicleRepair);
        if (vehicleRepair.getMechanic() == null) {
            vehicleRepair.setMechanic(this);
        }
    }

    public void removeVehicleRepair(VehicleRepair vehicleRepair) {
        if (vehicleRepair == null) {
            throw new IllegalArgumentException("Empty vehicle repair cannot be removed from the history.");
        }

        if (!this.vehicleRepairs.contains(vehicleRepair)) {
            return;
        }
        this.vehicleRepairs.remove(vehicleRepair);
        vehicleRepair.setMechanic(null);
    }

    @Override
    public String toString() {
        return "Mechanic ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nAge: " + getAge() +
                "\nHas valid work health and safety training: " + isWorkHealthAndSafetyTrainingValid() +
                "\nTechnical skills: " + technicalSkills;
    }
}
