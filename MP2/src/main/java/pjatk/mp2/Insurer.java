package pjatk.mp2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pjatk.mp2.Utils.*;

public class Insurer {
    private UUID id;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;
    private Set<CarInsurance> carInsurances = new HashSet<>();

    public Insurer(String companyName, String phoneNumber, String emailAddress) {
        setId();
        setCompanyName(companyName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        checkCorrectnessOfStringAttribute("Company name", companyName);
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        checkCorrectnessOfStringAttribute("Phone number", phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        checkCorrectnessOfStringAttribute("Email address", emailAddress);
        this.emailAddress = emailAddress;
    }

    public Set<CarInsurance> getCarInsurances() {
        return Collections.unmodifiableSet(carInsurances);
    }

    public void addCarInsurance(CarInsurance carInsurance) {
        if (carInsurance == null) {
            throw new IllegalArgumentException();
        }

        if (this.carInsurances.contains(carInsurance)) {
            return;
        }

        this.carInsurances.add(carInsurance);
        if (carInsurance.getInsurer() == null) {
            carInsurance.setInsurer(this);
        }
    }

    public void removeCarInsurance(CarInsurance carInsurance) {
        if (carInsurance == null) {
            throw new IllegalArgumentException("Empty car insurance cannot be removed from the history.");
        }

        if (!this.carInsurances.contains(carInsurance)) {
            return;
        }
        this.carInsurances.remove(carInsurance);
        carInsurance.setInsurer(null);
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Insurer's name: " + companyName +
                "\nPhone number: " + phoneNumber +
                "\nE-mail address: " + emailAddress;
    }
}
