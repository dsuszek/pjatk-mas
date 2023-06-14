package MP3.multi_inheritance;

import java.util.Collections;
import java.util.List;

public class WhiteCollarWorker extends Employee {
    private List<String> softSkills;
    private boolean isSightTestPassed;

    public WhiteCollarWorker(String firstName, String lastName, List<String> softSkills, boolean sightTestPassed) {
        super(firstName, lastName);
        setSoftSkills(softSkills);
        setIsSightTestPassed(sightTestPassed);
    }

    public List<String> getSoftSkills() {
        return Collections.unmodifiableList(softSkills);
    }

    public void setSoftSkills(List<String> softSkills) {
        this.softSkills = softSkills;
    }

    public boolean isSightTestPassed() {
        return isSightTestPassed;
    }

    public void setIsSightTestPassed(boolean sightTestPassed) {
        this.isSightTestPassed = sightTestPassed;
    }

    @Override
    public String toString() {
        return "White collar worker ID: " + getId() + "\n" +
                "- first name: " + getFirstName() + "\n" +
                "- last name: " + getLastName() + ".\n" +
                "- soft skills: " + getSoftSkills() + "\n" +
                "- valid sight test: " + isSightTestPassed();
    }
}
