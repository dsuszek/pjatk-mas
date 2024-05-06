package pjatk.mp3;

import java.time.LocalDate;
import java.util.Set;

import static pjatk.mp3.Utils.*;

public class MarketingSpecialist extends OfficeWorker {

    private String portfolioDescription;
    private String previousCampaignsDescription;
    public MarketingSpecialist(String firstName, String lastName, LocalDate birthDate, Set<String> officeWorkSkills, LocalDate dateUntilWhenSightTestIsValid, String portfolioDescription, String previousCampaignsDescription) {
        super(firstName, lastName, birthDate, officeWorkSkills, dateUntilWhenSightTestIsValid);
        try {
            setPortfolioDescription(portfolioDescription);
            setPreviousCampaignsDescription(previousCampaignsDescription);
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public String getPortfolioDescription() {
        return portfolioDescription;
    }

    public void setPortfolioDescription(String portfolioDescription) {
        checkCorrectnessOfStringAttribute(portfolioDescription, "Description of portfolio");
        this.portfolioDescription = portfolioDescription;
    }

    public String getPreviousCampaignsDescription() {
        return previousCampaignsDescription;
    }

    public void setPreviousCampaignsDescription(String previousCampaignsDescription) {
        checkCorrectnessOfStringAttribute(previousCampaignsDescription, "Description of previous campaigns");
        this.previousCampaignsDescription = previousCampaignsDescription;
    }

    @Override // Przesłonięcie metody.
    public String toString() {
        return "Marketing specialist ID: " + id +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nAge: " + getAge() +
                "\nSkills relevant for office work: " + getOfficeWorkSkills().toString() +
                "\nHas valid sight test: " + isSightTestValid() +
                "\nPortfolio description: " + getPortfolioDescription() +
                "\nPrevious campaigns description: " + getPreviousCampaignsDescription();
    }
}
