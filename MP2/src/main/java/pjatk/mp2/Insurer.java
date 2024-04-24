package pjatk.mp2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pjatk.mp2.Utils.*;

public class Insurer extends ObjectPlus {
    private UUID id;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;
    private Set<InsurancePolicy> insurancePolicies = new HashSet<>();

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

    public Set<InsurancePolicy> getInsurancePolicies() {
        return Collections.unmodifiableSet(insurancePolicies);
    }

    public void addInsurancePolicy(InsurancePolicy insurancePolicy) throws Exception {
        if (insurancePolicy == null) {
            throw new IllegalArgumentException("Empty insurance policy cannot be added to the history.");
        }

        if (this.insurancePolicies.contains(insurancePolicy)) {
            return;
        }

        this.insurancePolicies.add(insurancePolicy);
        if (insurancePolicy.getInsurer() == null) {
            insurancePolicy.setInsurer(this);
        }
    }

    public void removeInsurancePolicy(InsurancePolicy insurancePolicy) throws Exception {
        if (insurancePolicy == null) {
            throw new IllegalArgumentException("Empty insurance policy cannot be removed from the history.");
        }

        if (!this.insurancePolicies.contains(insurancePolicy)) {
            return;
        }
        this.insurancePolicies.remove(insurancePolicy);
        insurancePolicy.setInsurer(null);
    }
}
