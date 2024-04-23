package pjatk.mp2;

import java.util.UUID;

import static pjatk.mp2.Utils.*;

public class Insurer {
    private UUID id;
    private String companyName;
    private String phoneNumber;
    private String emailAddress;

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
}
