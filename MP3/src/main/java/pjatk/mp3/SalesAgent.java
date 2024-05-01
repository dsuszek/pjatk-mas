package pjatk.mp3;

import java.time.LocalDate;
import java.util.Set;

import static pjatk.mp3.Utils.checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero;

public class SalesAgent extends OfficeWorker {
    private double commission;

    public SalesAgent(String firstName, String lastName, LocalDate birthDate, Set<String> softSkills, LocalDate dateUntilWhenSightTestIsValid, double commission) {
        super(firstName, lastName, birthDate, softSkills, dateUntilWhenSightTestIsValid);
        try {
            setCommission(commission);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(commission, "Commission");
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "Sales agent ID: " + getId() +
                "\nFirst name: " + getFirstName() +
                "\nLast name: " + getLastName() +
                "\nAge: " + getAge() +
                "\nSoft skills: " + getSoftSkills() +
                "\nHas valid sight test: " + isSightTestValid();
    }
}
