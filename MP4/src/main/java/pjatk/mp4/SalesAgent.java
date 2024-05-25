package pjatk.mp4;

import java.time.LocalDate;
import java.util.Set;

import static pjatk.mp4.Utils.checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero;

public class SalesAgent extends OfficeWorker {
    private double commission;

    public SalesAgent(String firstName, String lastName, LocalDate birthDate, Set<String> softSkills, LocalDate dateUntilWhenSightTestIsValid, double commission, CompanyBranch companyBranch) {
        super(firstName, lastName, birthDate, softSkills, dateUntilWhenSightTestIsValid);
        try {
            setCommission(commission);
            addLinkXor("Employee", "Employer", companyBranch);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public SalesAgent(String firstName, String lastName, LocalDate birthDate, Set<String> softSkills, LocalDate dateUntilWhenSightTestIsValid, double commission, InsuranceCompany insuranceCompany) {
        super(firstName, lastName, birthDate, softSkills, dateUntilWhenSightTestIsValid);
        try {
            setCommission(commission);
            addLinkXor("Employee", "Employer", insuranceCompany);
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
        return "Sales agent ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nAge: " + getAge() +
                "\nSkills relevant for office work: " + getOfficeWorkSkills().toString() +
                "\nHas valid sight test: " + isSightTestValid() +
                "\nCommission: " + commission;
    }
}

