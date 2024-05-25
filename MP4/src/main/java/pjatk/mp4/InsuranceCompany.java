package pjatk.mp4;

import java.util.*;

import static pjatk.mp4.Utils.*;

public class InsuranceCompany extends ObjectPlus4 {
    private UUID id;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;
    private List<VehicleInsurance> vehicleInsurances = new ArrayList<>();

    public InsuranceCompany(String companyName, String phoneNumber, String emailAddress) {
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

    public List<VehicleInsurance> getVehicleInsurances() {
        return Collections.unmodifiableList(vehicleInsurances);
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
        return "Name of the insurance company: " + companyName +
                "\nPhone number: " + phoneNumber +
                "\nE-mail address: " + emailAddress;
    }
}
