package multi_inheritance;

import java.util.List;

public class GarageManager extends WhiteCollarWorker implements IBlueCollarWorker {

    private List<String> technicalSkills;

    public GarageManager(String firstName, String lastName, List<String> softSkills, boolean sightTestPassed, List<String> technicalSkills) {
        super(firstName, lastName, softSkills, sightTestPassed);
        setTechnicalSkills(technicalSkills);
    }

    @Override
    public void setTechnicalSkills(List<String> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    @Override
    public List<String> getTechnicalSkills() {
        return technicalSkills;
    }

    @Override
    public void addTechnicalSkill(String technicalSkill) {
        technicalSkills.add(technicalSkill);
    }

    @Override
    public String toString() {
        return "Garage manager ID: " + getId() + "\n" +
                "- first name: " + getFirstName() + "\n" +
                "- last name: " + getLastName() + "\n" +
                "- soft skills: " + getSoftSkills() + "\n" +
                "- technical skills: " + getTechnicalSkills() + "\n" +
                "- valid sight test: " + isSightTestPassed();
    }
}
