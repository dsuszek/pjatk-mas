package pjatk.mp4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pjatk.mp4.Utils.*;

public class Insurer extends ObjectPlus {
    private UUID id;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;
    private Set<VehicleInsurance> vehicleInsurances = new HashSet<>();

    public Insurer(String companyName, String phoneNumber, String emailAddress) {
        super();
        try {
            setId();
            setCompanyName(companyName);
            setPhoneNumber(phoneNumber);
            setEmailAddress(emailAddress);
        } catch (Exception e) {
            removeFromExtent();
        }
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

    public Set<VehicleInsurance> getVehicleInsurances() {
        return Collections.unmodifiableSet(vehicleInsurances);
    }

    public void addVehicleInsurance(VehicleInsurance vehicleInsurance) {
        if (vehicleInsurance == null) {
            throw new IllegalArgumentException();
        }

        if (this.vehicleInsurances.contains(vehicleInsurance)) {
            return;
        }

        this.vehicleInsurances.add(vehicleInsurance);
        if (vehicleInsurance.getInsurer() == null) {
            vehicleInsurance.setInsurer(this);
        }
    }

    public void removeVehicleInsurance(VehicleInsurance vehicleInsurance) {
        if (vehicleInsurance == null) {
            throw new IllegalArgumentException("Empty car insurance cannot be removed from the history.");
        }

        if (!this.vehicleInsurances.contains(vehicleInsurance)) {
            return;
        }
        this.vehicleInsurances.remove(vehicleInsurance);
        vehicleInsurance.setInsurer(null);
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Insurer's name: " + companyName +
                "\nPhone number: " + phoneNumber +
                "\nE-mail address: " + emailAddress;
    }
}
